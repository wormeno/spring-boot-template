package service.rest.controller.generic.mixin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.response.ResponseModel;
import service.rest.model.EntidadPersistente;

import javax.validation.Valid;

@Validated
public interface DeleteDto<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDTO> extends BasicsBehaviorDto<D,E,ID, ReturnDTO> {

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    default ResponseModel<Void> delete(@Valid @PathVariable ID id){
        return deleteDtoEntity(id);
    }
}
