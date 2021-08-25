package template.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import template.exception.GenericException;
import template.model.EntidadPersistente;
import java.time.LocalDateTime;
import java.util.*;

import static template.utils.UtilsTemplate.getFieldsWithValue;
import static template.utils.UtilsTemplate.mergeObject;

@Transactional
public abstract class GenericEntityService<E extends EntidadPersistente ,ID> /*implements GenericServices<E,ID>*/ {

    protected abstract JpaRepository<E, ID> getRepository();


/*    public void GenericService(JpaRepository<E,ID> repository, EntidadPersistente<ID> service){
        this.repository = repository;
        this.service = service;
    }*/


    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("No se puede actualizar un registro existente : "+entity.getId());
        entity.setCreateDate(LocalDateTime.now());
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

       /* entity.setId((Long) id);
        entity.setCreateDate(entityStored.get().getCreateDate());
        entity.setLastModifiedDate(LocalDateTime.now());*/
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
        return this.getRepository().save(entity);
    }

    public void delete(ID id) {
        Optional<E> entityStored = this.getRepository().findById(id);
        if( !entityStored.isPresent())
            throw new GenericException("Resource '"+id+"' Not Found");

        this.getRepository().delete(entityStored.get());
    }




    public Optional findById(ID id) {
        return this.getRepository().findById(id);
    }





    public List<E> find() {
        return this.getRepository().findAll();
    }

    /*

    public class UserMap extends PropertyMap<User, User> {
        protected void configure() {
            skip().setId(null);
        }
    }
*/

}
