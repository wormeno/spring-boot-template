package template.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "aux_estado")
public class Estado extends EntidadPersistente{

    private String nombre;
}
