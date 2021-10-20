package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PutDto<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto> extends BasicsBehaviorDto<D,E,ID, ReturnDto> {

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> update(@Valid @PathVariable ID id, @RequestBody D entityUpdate){
        return updateDtoEntity(id,entityUpdate);
    }
}
