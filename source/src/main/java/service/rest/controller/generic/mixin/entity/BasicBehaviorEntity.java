package service.rest.controller.generic.mixin.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.rest.response.ResponseModel;
import service.rest.model.EntidadPersistente;
import service.rest.service.generic.GenericEntityService;

import java.net.URI;

public interface BasicBehaviorEntity <E extends EntidadPersistente,ID>{

    public default GenericEntityService< E, ID> getService() {
        return null;
    }


    default ResponseEntity<?> saveEntity(E saveEntity) {
        E entity = getService().save( saveEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public default ResponseEntity<?> updateEntity( ID id, E updateEntity) {
        E entity = getService().update(id,updateEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public default ResponseEntity<?> partialUpdateEntity( ID id, E entityPartialUpdate ){
        E entity = getService().partialUpdate(id,entityPartialUpdate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    public default ResponseModel<Void> deleteEntity(ID id){
        getService().delete(id);
        return null;
    }
}
