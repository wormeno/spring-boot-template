package service.rest.controller.generic.mixin.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.rest.dto.DtoPersistente;
import service.rest.response.ResponseModel;
import service.rest.model.EntidadPersistente;
import service.rest.service.generic.GenericDtoService;

import java.net.URI;

public interface BasicsBehaviorDto<D extends DtoPersistente,E extends EntidadPersistente,ID, ReturnDto>{

    public default GenericDtoService<D, E, ID, ReturnDto> getService() {
        return null;
    }

    default ResponseEntity<?> saveDtoEntity(D saveEntity) {
        D entity = getService().save( saveEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    default ResponseEntity<?> updateDtoEntity(ID id, D update) {
        D entity = getService().update(id,update);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    default ResponseEntity<?> partialUpdateDtoEntity(ID id, D entityPartialUpdate ){
        D entity = getService().partialUpdate(id,entityPartialUpdate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    default ResponseModel<Void> deleteDtoEntity(ID id){
        getService().delete(id);
        return null;
    }

    default ResponseEntity<?> findDtoById(ID id){
        D entityDto = getService().findDtoById(id);
        return ResponseEntity.ok(entityDto);
    }

}
