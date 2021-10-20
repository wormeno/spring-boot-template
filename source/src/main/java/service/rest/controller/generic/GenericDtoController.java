package service.rest.controller.generic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;
import service.rest.response.ResponseModel;
import service.rest.service.generic.GenericDtoService;

import javax.validation.Valid;
import java.net.URI;


public abstract  class GenericDtoController <D extends DtoPersistente,E extends EntidadPersistente, ID> implements DefineRestBasicServices<D,ID> {

    protected abstract GenericDtoService<D,E, ID, DtoPersistente> getService();



    public ResponseEntity<?> save(@Valid @RequestBody D saveEntity) {

        D entity = getService().save( saveEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<?> update(@Valid @PathVariable ID id,@RequestBody D update) {
        D entity = getService().update(id,update);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    public ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody D partialUpdate ) {
        D entity = getService().partialUpdate(id,partialUpdate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public @ResponseBody ResponseModel<Void> delete(@Valid @PathVariable ID id){
        getService().delete(id);
       // return new ResponseEntity<String>(HttpStatus.OK);
        return null;
    }

    public ResponseEntity<?> find() {
        return null;
    }


}
