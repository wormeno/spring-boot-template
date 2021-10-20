package service.rest.controller.generic.mixin.dto;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.rest.dto.DtoPersistente;
import service.rest.dto.page.FilterDto;
import service.rest.dto.page.ResponsePaginator;
import service.rest.model.EntidadPersistente;
import service.rest.service.generic.GenericDtoService;

import javax.validation.Valid;

public interface GetDtoByCriteriaOneToMany <D extends DtoPersistente,E extends EntidadPersistente,P extends FilterDto, CustomDto,ID>{

    default GenericDtoService<D, E, ID, CustomDto> getService() {
        return null;
    }
    void setFkParent(D entity, ID fkValue);

    /**
     * Retorna los registros que cumplen con el criterio solicitado.
     * Los criterios de búsqueda son opcionales
     * @param pageable Filters Criteria with page
     * @return Resultados paginados con los Dto configurados
     */
    @ApiOperation(value = "Find by criteria",tags = "findByCriteria")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    default ResponsePaginator<?> findDtoByCriteria(@Valid @PathVariable ID idParent,@Valid P pageable){
        return  getService().findDtoByCriteria(pageable,idParent);
    }


}
