package template.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Data
@Entity(name = "barrios")
public class Barrio extends EntidadPersistente {

    @NonNull
    @NotEmpty(message = "Debe ingresar el nombre")
    private String nombre;

    @NonNull
    private String descripcion;

    public Barrio(){}

}
