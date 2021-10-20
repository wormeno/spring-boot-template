package service.rest.controller.generic.mixin.dto;

import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

public interface BasicsBehaviorDtoOneToMany<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto>
    extends BasicsBehaviorDto<D,E,ID, ReturnDto>{

    void setFkParent(D entity, ID fkValue);

}
