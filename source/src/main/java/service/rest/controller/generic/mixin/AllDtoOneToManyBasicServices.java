package service.rest.controller.generic.mixin;

import service.rest.controller.generic.mixin.dto.DeleteDtoOneToMany;
import service.rest.controller.generic.mixin.dto.PatchDtoOneToMany;
import service.rest.controller.generic.mixin.dto.PostDtoOneToMany;
import service.rest.controller.generic.mixin.dto.PutDtoOneToMany;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;

public interface AllDtoOneToManyBasicServices <D extends DtoPersistente,E extends EntidadPersistente,ID,ReturnDto>
    extends DeleteDtoOneToMany<D,E,ID,ReturnDto>, PatchDtoOneToMany<D,E,ID,ReturnDto>, PostDtoOneToMany<D,E,ID,ReturnDto>
        , PutDtoOneToMany<D,E,ID,ReturnDto> {

    //Es un Simple "PasaManos" que agrupa todas las implementaciones OneToMany
}
