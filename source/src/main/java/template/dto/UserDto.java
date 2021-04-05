package template.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {

    @ApiModelProperty(example = "25881112225", name = "cuil", value = "Ingrese el cuil", required = true)
    @JsonIgnore
    private String cuil;
    @ApiModelProperty(example = "25", name = "barrio", value = "Ingrese el Barrio", required = true)
    private Integer barrio;

}
