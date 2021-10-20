package service.rest.service.generic;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import service.rest.dto.page.FilterDto;
import service.rest.dto.page.ResponsePaginator;
import service.rest.model.EntidadPersistente;
//import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericDtoService <D,E extends EntidadPersistente,ID, ReturnDto> extends GenericEntityService<E,ID> {

    protected ModelMapper modelMapper = new ModelMapper() ;

    public abstract E mapperDtoToEntity(D dtoEntity);

    public abstract D mapperEntityToDto(E entity);

    public  ReturnDto mapperEntityToCustomDto(E entity){
        return (ReturnDto) mapperEntityToDto(entity);
    }

    protected void applyBusinessRules(D dtoEntity){

    }

    //@Override
    public D save(D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        applyBusinessRules(dtoEntity);

        super.save(entity);
        D newDtoEntity = mapperEntityToDto(entity);

        return newDtoEntity;
    }


    public D update(ID id, D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        applyBusinessRules(dtoEntity);
        super.update(id, entity);
        D dtoEntity2 = mapperEntityToDto(entity);
        return dtoEntity2;
    }

    public D partialUpdate(ID id, D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        applyBusinessRules(dtoEntity);
        super.partialUpdate(id, entity);
        D dtoEntity2 = mapperEntityToDto(entity);
        return dtoEntity2;
    }

    public D findDtoById(ID id) {
        E entity = super.findById(id);

        D entityDto =  mapperEntityToDto(entity);
        return entityDto;
    }

    public ReturnDto findCustomDtoById(ID id){
        E entity = super.findById(id);
        ReturnDto entityDto = mapperEntityToCustomDto(entity);
        return entityDto;
    }

    public ResponsePaginator<?> findDtoByCriteria(FilterDto pageable){
        Page<E> resultEntities =  findByCriteria((FilterDto) pageable);
        return returnDtoResponsePaginator(resultEntities);
    }

    public ResponsePaginator<?> findDtoByCriteria(FilterDto pageable,ID parentFk){
        Page<E> resultEntities =  findByCriteria(pageable, parentFk);
        return returnDtoResponsePaginator((Page<E>) resultEntities);
    }

    private ResponsePaginator<?> returnDtoResponsePaginator(Page<E> resultEntities) {
        ResponsePaginator<ReturnDto> responsePaginator = (ResponsePaginator<ReturnDto>) createResponsePaginator(resultEntities);

        List<ReturnDto> reultDto = resultEntities.getContent().stream()
                .map(unContenido -> mapperEntityToCustomDto(unContenido))
                .collect(Collectors.toList());

        responsePaginator.setContent(reultDto);

        return responsePaginator;
    }

    private ResponsePaginator<?> createResponsePaginator(Page<E> resultEntities){
        ResponsePaginator<?> responsePaginator= new ResponsePaginator<>();
        responsePaginator.setTotalPage(resultEntities.getTotalPages());
        responsePaginator.setTotalRecords(resultEntities.getTotalElements());
        responsePaginator.setPageNumber(resultEntities.getPageable().getPageNumber());
        responsePaginator.setPageSize(resultEntities.getPageable().getPageSize());
        return responsePaginator;
    }

    /*

    public Page<D> findAllDto(Pageable pageable){
        Page<E> result = super.findByCriteria(pageable);
        return  new PageImpl<D> (
                result.getContent().stream()
                        .map(
                                entity -> mapperEntityToDto(entity)
                        ).collect(Collectors.toList()),
                pageable, result.getTotalElements()
        );
    }
*/


/*

    public ResponsePaginator<D> findDtoByCriteria(FilterDto pageable){
        Page<E> resultEntities =  findByCriteria((FilterDto) pageable);
        ResponsePaginator<D> responsePaginator = (ResponsePaginator<D>) createResponsePaginator(resultEntities);

        List<D> reultDto = resultEntities.getContent().stream()
                .map( unContenido -> mapperEntityToDto(unContenido) )
                .collect(Collectors.toList());

        responsePaginator.setContent(reultDto);
        return responsePaginator;
    }
*/

}
