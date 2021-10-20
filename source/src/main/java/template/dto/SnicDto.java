package template.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import template.Validation.YearConstraint;
import template.model.Estado;
import template.service.EstadoSnicService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class SnicDto extends DtoPersistente{

    @NotNull( message = "Debe ingresar un valor para el Departamento" )
    @ApiModelProperty(example = "2", name = "departamento", value = "Ingrese el Departamento", required = true)
    private Long departamentoId;

    @NotNull( message = "Debe ingresar un valor para la Seccional" )
    @ApiModelProperty(example = "2", name = "seccional", value = "Ingrese la Seccional", required = true)
    private Long seccionalId;

    @NotNull( message = "Debe ingresar un valor para la Año" )
    @ApiModelProperty(example = "2021", name = "año", value = "Ingrese el Año", required = true)
    @Pattern(regexp = "^[0-9]{4}$",message = "El Año debe ser un número")
//    @Size(min = 2013,message = "El Año debe ser mayor a 2013")
    @YearConstraint(message = "El Año debe ser mayor a 2013 y menor o igual al año actual")
    private String anio;

    @NotNull( message = "Debe ingresar un valor para el Mes" )
    @ApiModelProperty(example = "1", name = "mes", value = "Ingrese el Mes", required = true)
    @Range(min = 1,max = 12)
    private Long mesId;

    @JsonIgnore
    private Long estadoId = getEstadoId();//El valor por default es 1

    @NotNull( message = "Debe ingresar un valor para Registra Delitos" )
    @ApiModelProperty(example = "1", name = "registraDelitos", value = "Ingrese Registra Delitos", required = true)
    private Boolean registraDelitos;



    @JsonIgnore
    @Autowired
    EstadoSnicService estadoSnicService;
    @JsonIgnore
    private Integer getEstado(){
        Estado estado = estadoSnicService.getEstadoEnEdicion();
        return estado.getId().intValue();
    }
}
