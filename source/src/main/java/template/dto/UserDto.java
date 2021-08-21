package template.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends DtoPersistente {

    @JsonIgnore
    private Long id;

    @ApiModelProperty( example = "25881112225", name = "cuil", value = "Ingrese el cuil", required = true )
    @NotNull( message = "Debe ingresar un valor para el cuil" )
    @Min(13)
    private String cuil;

    @NotNull( message = "Debe ingresar un valor para el barrio" )
    @ApiModelProperty(example = "25", name = "barrio", value = "Ingrese el Barrio", required = true)
    private Long barrioId;



}
