package service.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@Component
public class DaoQuery<E> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    private String select;
    private String from;
    private List<String> whereList;
    private HashMap<String,Object> params;
    private Integer pageNumber = 0;
    private Integer pageSize = 20;
//
//    public DaoQuery(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
//        this.entityManager = entityManager;
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public DaoQuery setSelect(String selectRow){
        this.select = selectRow;
        return this;
    }

    public DaoQuery setFrom(String from){
        this.from = from;
        return this;
    }

    public DaoQuery setWhereList(List<String> whereList){
        this.whereList = whereList;
        return this;
    }
    public DaoQuery setParams(HashMap<String,Object> params){
        this.params = params;
        return this;
    }

    public DaoQuery setPageNumber(Integer pageNumber) {
        if(pageNumber != null){
            this.pageNumber = pageNumber;
        }
        return this;
    }
    public DaoQuery setPageSize(Integer pageSize) {
        if(pageSize != null)
            this.pageSize = pageSize;
        return this;
    }

    public List<E> selectResultList(){
        Query jpaQuery =createQuery(select);
        List<E> result = jpaQuery.getResultList();
        return result;
    }

    public Long countResultList(){
        Query query =createQuery(" count(*) ");
        List<Long> result = query.getResultList();
        return result.get(0);
    }

    public PageImpl<E> getResultList(){
        PageImpl<E> result = new PageImpl<E>(selectResultList(), PageRequest.of(pageNumber,pageSize), countResultList());
        return result;

    }

    private Query createQuery(String selectRows){
        StringBuilder queryBuilder = new StringBuilder("select ");
        queryBuilder.append(selectRows);
        queryBuilder.append(" "+ from+" " );

        if( whereList != null && whereList.size() >0 ){
            String joinStr = (whereList.size() == 1 ) ? " ": " and ";

            queryBuilder.append(" and "+ String.join( joinStr ,whereList) +" " );
        }

        Query jpaQuery = entityManager.createQuery(queryBuilder.toString())
                .setMaxResults(pageSize)
                .setFirstResult(pageNumber * pageSize);

        params.forEach((key, value) ->
                jpaQuery.setParameter(key,value)
        );

        return jpaQuery;
    }

}
