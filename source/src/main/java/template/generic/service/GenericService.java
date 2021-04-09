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
//    @Autowired
    protected JpaRepository<E,ID> repository;

    public void genericService(JpaRepository<E,ID> repository){
        this.repository = repository;

    }

  /*  protected abstract GenericService<E, ID> getService();

    protected abstract JpaRepository<E, ID> getRepository();*/

//    protected abstract LocalDate getCreateDate(ID id);
    protected LocalDate getCreateDate(ID id){
        if( id == null)
            throw new GenericException("Se debe indicar identificador del registro");

        return repository.findById(id).get().getCreateDate();

    }

    public Page<E> findAllPageable(int page, int size) {
        return this.repository.findAll(PageRequest.of(page, size));
    }

    public Page<E> findAll() {
        return repository.findAll(Pageable.unpaged());
    }

    public List<E> findAllList() {
        return repository.findAll();
    }

    public Optional<E> find(ID id) {
        return repository.findById(id);
    }

    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("No se puede actualizar un registro existente : "+entity.getId());

        entity.setCreateDate(LocalDate.now());
        return repository.save(entity);
    }

    public E update(E entity) {
        if (entity.getId() == null)
            throw new GenericException("No existe el registro que desea actualizar");

        Optional<E> entityStored = repository.findById((ID) entity.getId());
        entity.setId(entityStored.get().getId());
        entity.setLastModifiedDate(LocalDate.now());
        entity.setCreateDate(this.getCreateDate((ID) entity.getId()));

        return repository.save(entity);
    }

    public void remove(ID id) {
        Optional<E> recurso = repository.findById(id);

        if (!recurso.isPresent()) {
            throw new GenericException("No existe la entidad que se quiere borrar.");
        }

        repository.delete(recurso.get());
    }




}
