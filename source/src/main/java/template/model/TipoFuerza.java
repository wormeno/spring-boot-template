package template.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "aux_tipo_fuerza")
public class TipoFuerza extends EntidadPersistente{

    private String nombre;
    private String sigla;

    @Column(name = "fuerza_federal")
    private Boolean fuerzaFederal;

}
