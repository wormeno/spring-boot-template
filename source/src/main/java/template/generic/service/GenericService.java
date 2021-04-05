package template.generic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import template.generic.exeption.GenericException;
import template.generic.model.EntidadPersistente;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class GenericService<E extends EntidadPersistente,ID> {
    @Autowired
    protected JpaRepository<E,ID> repository;
    protected GenericService<E,ID> service;

    public void genericService(JpaRepository<E,ID> repository,GenericService<E,ID> service){
        this.repository = repository;
        this.service = service;
    }

  /*  protected abstract GenericService<E, ID> getService();

    protected abstract JpaRepository<E, ID> getRepository();*/

//    protected abstract LocalDate getCreateDate(ID id);
    protected LocalDate getCreateDate(ID id){
        if( id == null)
            throw new GenericException("Se debe indicar identificador del registro");

        return this.getRepository().findById(id).get().getCreateDate();

    }

    public Page<E> findAllPageable(int page, int size) {
        return this.getRepository().findAll(PageRequest.of(page, size));
    }

    public Page<E> findAll() {
        return this.getRepository().findAll(Pageable.unpaged());
    }

    public List<E> findAllList() {
        return this.getRepository().findAll();
    }

    public Optional<E> find(ID id) {
        return this.getRepository().findById(id);
    }

    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("No se puede actualizar un registro existente : "+entity.getId());

        entity.setCreateDate(LocalDate.now());
        return this.getRepository().save(entity);
    }

    public E update(E entity) {
        if (entity.getId() == null)
            throw new GenericException("No existe el registro que desea actualizar");

        Optional<E> entityStored = this.getRepository().findById((ID) entity.getId());
        entity.setId(entityStored.get().getId());
        entity.setLastModifiedDate(LocalDate.now());
        entity.setCreateDate(this.getCreateDate((ID) entity.getId()));

        return this.getRepository().save(entity);
    }

    public void remove(ID id) {
        Optional<E> recurso = this.getRepository().findById(id);

        if (!recurso.isPresent()) {
            throw new GenericException("No existe la entidad que se quiere borrar.");
        }

        this.getRepository().delete(recurso.get());
    }




}
