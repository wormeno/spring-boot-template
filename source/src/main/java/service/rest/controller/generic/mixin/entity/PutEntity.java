package service.rest.controller.generic.mixin.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PutEntity <E extends EntidadPersistente,ID> extends BasicBehaviorEntity<E,ID>{

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> update(@Valid @PathVariable ID id, @RequestBody E entityUpdate){
        return updateEntity(id,entityUpdate);
    }
}
