package service.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import service.rest.Validation.CuitConstraint;

import javax.validation.constraints.*;

//@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends DtoPersistente {

    @JsonIgnore
    private Long id;

    @ApiModelProperty( example = "25881112225", name = "cuil", value = "Ingrese el cuil", required = true )
    @NotNull( message = "Debe ingresar un valor para el cuil" )
    @CuitConstraint(message = "El cuil debe ser valido")
    private String cuil;

    @NotNull( message = "Debe ingresar un valor para el barrio" )
    @ApiModelProperty(example = "25", name = "barrio", value = "Ingrese el Barrio", required = true)
    private Long barrioId;

}
