package service.rest.controller.generic;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.rest.dto.page.FilterDto;
import service.rest.model.EntidadPersistente;
import service.rest.response.ResponseModel;

import service.rest.service.generic.GenericEntityService;

import javax.validation.Valid;
import java.net.URI;

public abstract class GenericEntityController <E extends EntidadPersistente,ID>  implements DefineRestBasicServices<E,ID> {

    protected abstract GenericEntityService<E, ID> getService();

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody E entity ) {
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
    public Page<?> findAll(FilterDto pageable) {
        return  getService().findByCriteria(pageable);
    }


    public ResponseModel<Void> delete(Long id) {
        return null;
    }
}
