package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface PostDto<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDTO> extends BasicsBehaviorDto<D,E,ID, ReturnDTO> {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public default ResponseEntity<?> save(@Valid  @RequestBody D entity){
        return saveDtoEntity(entity);
    }
}
