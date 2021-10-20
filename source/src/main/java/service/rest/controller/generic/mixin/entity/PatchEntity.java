package service.rest.controller.generic.mixin.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PatchEntity <E extends EntidadPersistente,ID> extends BasicBehaviorEntity<E,ID> {

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> partialUpdate(@Valid @PathVariable ID id, @RequestBody E entityPartialUpdate ){
        return partialUpdateEntity(id,entityPartialUpdate);
    }
}
