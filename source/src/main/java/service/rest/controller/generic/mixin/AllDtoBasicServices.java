package service.rest.controller.generic.mixin;


import service.rest.controller.generic.mixin.dto.DeleteDto;
import service.rest.controller.generic.mixin.dto.PatchDto;
import service.rest.controller.generic.mixin.dto.PostDto;
import service.rest.controller.generic.mixin.dto.PutDto;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

public  interface AllDtoBasicServices<D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDTO> extends PutDto<D, E, ID,ReturnDTO>,
        DeleteDto<D,E,ID,ReturnDTO>, PatchDto<D,E,ID,ReturnDTO>, PostDto<D,E,ID,ReturnDTO> {

    //Es un Simple "PasaManos" que agrupa todas las implementaciones
}
