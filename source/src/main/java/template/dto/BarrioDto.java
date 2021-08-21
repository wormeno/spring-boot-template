package template.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class BarrioDto implements Serializable {
    String nombre;
    String descripcion;
}
