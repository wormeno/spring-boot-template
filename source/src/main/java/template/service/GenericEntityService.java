package template.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import template.exception.GenericException;
import template.model.EntidadPersistente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            throw new GenericException("No existe el recurso");

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
/*

    public class UserMap extends PropertyMap<User, User> {
        protected void configure() {
            skip().setId(null);
        }
    }
*/

    public E partialUpdate(ID id, E entity){
        Optional<E> entityStored = this.getRepository().findById(id);
        if( !entityStored.isPresent())
            throw new GenericException("No existe el recurso");

        entity.setId((Long) id);
        entity.setLastModifiedDate(LocalDateTime.now());
        return this.getRepository().save(entity);
    }


    public Optional findById(ID id) {
        return this.getRepository().findById(id);
    }


    public void delete(Object o) {

    }


    public List<E> find() {
        return this.getRepository().findAll();
    }

}
