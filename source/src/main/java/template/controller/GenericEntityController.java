package template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import template.model.EntidadPersistente;
import template.generic.response.ResponseModel;

import template.service.GenericEntityService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public abstract class GenericEntityController <E extends EntidadPersistente,ID>  implements GenericController<E,ID> {

    protected abstract GenericEntityService<E, ID> getService();

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody E entity , BindingResult bindingResult) {
        entity = getService().save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid  @PathVariable ID id, @RequestBody E entity ) {
        entity = getService().update(id,entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody E entity ) {
        entity = getService().partialUpdate(id,entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping("/{pageIndex}/{pageSize}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> find() {
        List<E> entity = getService().find();

        return ResponseEntity.ok(entity) ;
    }

/*
    @Override
    public ResponseEntity<?> find(Long id) {
        return null;
    }
*/

    public ResponseModel<Void> delete(Long id) {
        return null;
    }
}
