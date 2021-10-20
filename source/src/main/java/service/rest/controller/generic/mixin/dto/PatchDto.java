package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PatchDto<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDTO> extends BasicsBehaviorDto<D,E,ID, ReturnDTO> {

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody D entityPartialUpdate ){
        return partialUpdateDtoEntity(id,entityPartialUpdate);
    }

}
