package template.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import template.Validation.CuitConstraint;
import template.Validation.TipoDelitoSnicConstraint;
import template.Validation.YearConstraint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SnicDelitoDto extends DtoPersistente{

    @NotNull( message = "Debe ingresar un valor para el Tipo de delito" )
    @ApiModelProperty(example = "3", name = "Tipo delito Snic", value = "Ingrese el Tipo de delito", required = true)
    private String codigoTipoDelitoSnic;

    @NotNull( message = "Debe ingresar un valor para 'Por Denuncia Particular'" )
    @ApiModelProperty(example = "1", name = "Por Denuncia Particular", value = "Ingrese 'Por Denuncia Particular' ", required = true)
    @Min(value = 0, message = "sss")
    private Integer porDenunciaParticular;

    @NotNull( message = "Debe ingresar un valor para 'Por Intervencion Policial'" )
    @ApiModelProperty(example = "1", name = "Por Intervencion Policial", value = "Ingrese 'Por Intervencion Policial' ", required = true)
    @Min(0)
    private Integer porIntervencionPolicial;

    @NotNull( message = "Debe ingresar un valor para 'Por Orden Judicial'" )
    @ApiModelProperty(example = "1", name = "Por Orden Judicial", value = "Ingrese 'Por Orden Judicial' ", required = true)
    @Min(0)
    private Integer porOrdenJudicial;

    @NotNull( message = "Debe ingresar un valor para 'Otro no consta'" )
    @ApiModelProperty(example = "0", name = "Otro no consta", value = "Ingrese 'Otro no consta' ", required = true)
    @Min(0)
    private Integer otroNoConsta;

    @NotNull( message = "Debe ingresar un valor para 'Víctimas masculino'" )
    @ApiModelProperty(example = "0", name = "Víctimas masculino", value = "Ingrese 'Víctimas masculino' ", required = true)
    @Min(0)
    private Integer victimasMasculino;

    @NotNull( message = "Debe ingresar un valor para 'Víctimas femenino'" )
    @ApiModelProperty(example = "0", name = "Víctimas femenino", value = "Ingrese 'Víctimas femenino' ", required = true)
    @Min(0)
    private Integer victimasFemenino;

    @NotNull( message = "Debe ingresar un valor para 'Víctimas no consta'" )
    @ApiModelProperty(example = "3", name = "Víctimas no consta", value = "Ingrese 'Víctimas no consta' ", required = true)
    @Min(0)
    private Integer victimasNoConsta;

    @NotNull( message = "Debe ingresar un valor para 'Varon'" )
    @ApiModelProperty(example = "0", name = "Varon", value = "Ingrese 'Varon' ", required = true)
    @Min(0)
    private Integer varon;

    @NotNull( message = "Debe ingresar un valor para 'Mujer'" )
    @ApiModelProperty(example = "0", name = "Mujer", value = "Ingrese 'Mujer' ", required = true)
    @Min(0)
    private Integer mujer;

    @NotNull( message = "Debe ingresar un valor para 'Varon trans'" )
    @ApiModelProperty(example = "0", name = "Varon trans", value = "Ingrese 'Varon trans' ", required = true)
    @Min(0)
    private Integer varonTrans;

    @NotNull( message = "Debe ingresar un valor para 'Mujer trans'" )
    @ApiModelProperty(example = "0", name = "Mujer trans", value = "Ingrese 'Mujer trans' ", required = true)
    @Min(0)
    private Integer mujerTrans;

    @NotNull( message = "Debe ingresar un valor para 'Otro genero'" )
    @ApiModelProperty(example = "0", name = "Otro genero", value = "Ingrese 'Otro genero' ", required = true)
    @Min(0)
    private Integer generoOtro;

    @NotNull( message = "Debe ingresar un valor para 'Género sin determinar'" )
    @ApiModelProperty(example = "3", name = "Género sin determinar", value = "Ingrese 'Género sin determinar' ", required = true)
    @Min(0)
    private Integer generoSd;


}
