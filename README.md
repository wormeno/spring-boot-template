## Objetivos
* Mantener el Estándar de "Buenas Prácticas" cuando se crean Servicios Rest.
* Integraciones con sistemas satélites.

### Diagrama de clases

#### Implementación de los servicios Rest
* Implementación usando Herencia Simple

![Diagrama de clases](/documents/Files/DiagramClass.svg "Diagram class")

* Implementación usando Mixins
  ![Diagrama de clases](/documents/Files/ImplementatiosWithMixin.png "Diagram class")

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

