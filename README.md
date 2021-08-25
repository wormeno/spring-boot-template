
### Diagrama de manejo de excepciones

![Manejo de Excepciones](/documents/Files/HandlingExceptions.png "Handling Exceptions")

### Validaciones Personalizadas

![Validaciones Personalizadas](/documents/Files/CustomValidations.svg "CustomValidations")

Ejemplo:
    
    @ApiModelProperty( example = "25881112225", name = "cuil", value = "Ingrese el cuil", required = true )
    @NotNull( message = "Debe ingresar un valor para el cuil" )
    @CuitConstraint(message = "El cuil debe ser valido")
    private String cuil;


También es posible validar un campo en función del valor completado en otro campo

