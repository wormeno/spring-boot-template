package template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import template.generic.model.EntidadPersistente;

import javax.persistence.Entity;

@Data
@Entity(name = "barrios")
public class Barrio extends EntidadPersistente {


    private String nombre;


    private String descripcion;

}
