package template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import template.dto.DtoPersistente;
import template.model.EntidadPersistente;
import template.generic.response.ResponseModel;
import template.service.GenericDtoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public abstract  class GenericDtoController <D extends DtoPersistente,E extends EntidadPersistente, ID> implements GenericController<D,ID> {

    protected abstract GenericDtoService<D,E, ID> getService();

    public ResponseEntity<?> save(@Valid @RequestBody D saveEntity,BindingResult bindingResult) {

        D entity = getService().save( saveEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<?> update(@Valid @PathVariable ID id,@RequestBody D update) {
        D entity = getService().update(id,update);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

/*

    public ResponseEntity<?> update(@Valid @PathVariable Object id,@RequestBody Object entity) {
        return null;
    }

    public ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody Object entity ) {
        return null;
    }
*/



    public ResponseEntity<?> find() {
        return null;
    }


    public ResponseModel<Void> delete( Object id) {
        return null;
    }


    public ResponseEntity<?> partialUpdate(Long aLong, D partialUpdate) {
        return null;
    }

    public ResponseModel<Void> delete(Long aLong) {
        return null;
    }

}
