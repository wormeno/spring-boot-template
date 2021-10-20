package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;
import java.net.URI;

@Validated
public interface PutDtoOneToMany<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto>
        extends BasicsBehaviorDtoOneToMany<D,E,ID,ReturnDto> {


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> update(@Valid @PathVariable ID idParent,@PathVariable ID id, @RequestBody D entityUpdate){
        setFkParent(entityUpdate,idParent);
        D entity = getService().update(id,entityUpdate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
