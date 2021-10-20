package service.rest.controller.generic.mixin.dto;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.model.EntidadPersistente;
import service.rest.service.generic.GenericDtoService;

import javax.validation.Valid;

public interface GetCustomDtoById <D extends DtoPersistente,E extends EntidadPersistente, CustomDto,ID>{

    default GenericDtoService<D, E, ID, CustomDto> getService() {
        return null;
    }

    /**
     * Retorna los registros que cumplen con el criterio solicitado.
     * Los criterios de búsqueda son opcionales
     * @param ID
     * @return Resultados paginados con los registros con el formtato DTO: ReturnPageDto
     */
    @ApiOperation(value = "Find by id", tags = "findById")
    @GetMapping("/{id}/")
    @ResponseStatus(value = HttpStatus.OK)
    default ResponseEntity<CustomDto> findCustomDtoById(@Valid @PathVariable ID id){
        CustomDto entityDto = getService().findCustomDtoById( id);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entityDto).toUri();
        return ResponseEntity.ok(entityDto);
    }
}
