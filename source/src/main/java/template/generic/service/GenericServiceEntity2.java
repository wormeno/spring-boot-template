package template.generic.service;

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
public abstract class GenericServiceEntity2<E extends EntidadPersistente,ID> {
    //@Autowired
    protected JpaRepository<E,ID> repository;
    protected GenericServiceEntity2<E,ID> service;

    public void genericService(JpaRepository<E,ID> repository, GenericServiceEntity2<E,ID> service){
        this.repository = repository;
        this.service = service;
    }


    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("No se puede actualizar un registro existente : "+entity.getId());

        entity.setCreateDate(LocalDate.now());
        return this.repository.save(entity);
    }


  /*  protected abstract GenericService<E, ID> getService();

    protected abstract JpaRepository<E, ID> getRepository();*/

//    protected abstract LocalDate getCreateDate(ID id);
    protected LocalDate getCreateDate(ID id){
        if( id == null)
            throw new GenericException("Se debe indicar identificador del registro");

        return this.repository.findById(id).get().getCreateDate();

    }

    public Page<E> findAllPageable(int page, int size) {
        return this.repository.findAll(PageRequest.of(page, size));
    }

    public Page<E> findAll() {
        return this.repository.findAll(Pageable.unpaged());
    }

    public List<E> findAllList() {
        return this.repository.findAll();
    }

    public Optional<E> find(ID id) {
        return this.repository.findById(id);
    }



    public E update(E entity) {
        if (entity.getId() == null)
            throw new GenericException("No existe el registro que desea actualizar");

        Optional<E> entityStored = this.repository.findById((ID) entity.getId());
        entity.setId(entityStored.get().getId());
        entity.setLastModifiedDate(LocalDate.now());
        entity.setCreateDate(this.getCreateDate((ID) entity.getId()));

        return this.repository.save(entity);
    }

    public void remove(ID id) {
        Optional<E> recurso = this.repository.findById(id);

        if (!recurso.isPresent()) {
            throw new GenericException("No existe la entidad que se quiere borrar.");
        }

        this.repository.delete(recurso.get());
    }




}
