package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PostDtoOneToMany<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto>
        extends BasicsBehaviorDtoOneToMany<D,E,ID,ReturnDto> {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> save(@Valid @PathVariable ID idParent,@RequestBody D entity){
        setFkParent(entity,idParent);
        return saveDtoEntity(entity);
     }


}
