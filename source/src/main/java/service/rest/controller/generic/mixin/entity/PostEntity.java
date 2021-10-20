package service.rest.controller.generic.mixin.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PostEntity <E extends EntidadPersistente,ID> extends BasicBehaviorEntity<E,ID> {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> save(@Valid @RequestBody E entity){
        return saveEntity(entity);
    }
}
