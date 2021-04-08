package template.generic.service;

import org.springframework.data.jpa.repository.JpaRepository;
import template.generic.exeption.GenericException;
import template.generic.model.EntidadPersistente;

import java.time.LocalDate;

public abstract class GenericService <E ,ID> {

    protected JpaRepository<E,ID> repository;


    public void genericService(JpaRepository<E,ID> repository){
        this.repository = repository;

    }

    public E save(E entity) {
        if (entity.getId() != null)
            throw new GenericException("No se puede actualizar un registro existente : "+entity.getId());

        entity.setCreateDate(LocalDate.now());
        return this.repository.save(entity);
    }

}
