package template.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "aux_provincia")
public class Provincia extends EntidadPersistente{

    private String nombre;
    private String activo;
}
