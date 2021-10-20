package service.rest.service.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import service.rest.dto.page.FilterDto;
import service.rest.exception.GenericException;
import service.rest.businessRule.Action;
import service.rest.businessRule.ReglaDeNegocio;
import service.rest.model.EntidadPersistente;
import java.time.LocalDateTime;
import java.util.*;

import static service.rest.businessRule.Action.*;
import static service.rest.utils.UtilsTemplate.getFieldsWithValue;
import static service.rest.utils.UtilsTemplate.mergeObject;

@Transactional
public abstract class GenericEntityService<E extends EntidadPersistente,ID> {

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract ReglaDeNegocio<E> getBusinessRule();

    protected void applyBusinessRules(E entity, Action action){   }

    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("Save No permite actualizar el registro existente : "+entity.getId());
        entity.setCreateDate(LocalDateTime.now());
        applyBusinessRules(entity,POST);

        return getRepository().save(entity);
    }


    public E update(ID id, E entity){
        Optional<E> entityStored = this.getRepository().findById(id);
        if( !entityStored.isPresent())
            throw new GenericException("Resource '"+id+"' Not Found");

        entity.setId((Long) id);
        entity.setCreateDate(entityStored.get().getCreateDate());
        entity.setLastModifiedDate(LocalDateTime.now());

        Map<String,Object> fields = getFieldsWithValue(entity);

        fields.entrySet().stream()
                .forEach( unaEntidad -> {
                    if( unaEntidad.getValue() == null)
                        throw new GenericException("Debe completar "+unaEntidad.getKey().toString());
                });

        applyBusinessRules(entity,PUT);
        return this.getRepository().save(entity);
    }

    public E partialUpdate(ID id, E entity){
        Optional<E> entityStored = this.getRepository().findById(id);
        if( !entityStored.isPresent())
            throw new GenericException("Resource '"+id+"' Not Found");

        try {
            mergeObject(entity,entityStored.get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        entity.setLastModifiedDate(LocalDateTime.now());
        applyBusinessRules(entity,PATCH);
        return this.getRepository().save(entity);
    }

    public void delete(ID id) {
        Optional<E> entityStored = this.getRepository().findById(id);
        if( !entityStored.isPresent())
            throw new GenericException("Resource '"+id+"' Not Found");

//        applyBusinessRules(entityStored.get(),DELETE);
        getBusinessRule().delete(entityStored.get());
        this.getRepository().delete(entityStored.get());
    }


    public E findById(ID id) {
        Optional<E> entity = getRepository().findById(id);
        if ( !entity.isPresent() )
            throw new GenericException("Resource not found ");
        return entity.get();
    }

    public Page<E> findAll(FilterDto pageable){
        Pageable page = PageRequest.of(
                pageable.getPageNumber() == null ?0:pageable.getPageNumber(),
                pageable.getPageSize() == null ?20:pageable.getPageSize()
        );

        return getRepository().findAll(page);
    }

    public Page<E> findByCriteria(FilterDto pageable) {
        return findAll(pageable);
    }

    public Page<E> findByCriteria(FilterDto pageable, ID id) {
        return null;
    }

}
