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
public interface DeleteDtoOneToMany <D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto>
        extends BasicsBehaviorDtoOneToMany<D,E,ID,ReturnDto> {

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    default ResponseModel<Void> delete(@Valid @PathVariable ID idParent,@PathVariable ID id){
//TODO --> VER COMO UNIFICAR PARENT CON ID
        return deleteDtoEntity(id);
    }
}
