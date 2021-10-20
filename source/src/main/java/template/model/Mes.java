package template.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "aux_mes")
public class Mes extends EntidadPersistente{

    private String nombre;
}
