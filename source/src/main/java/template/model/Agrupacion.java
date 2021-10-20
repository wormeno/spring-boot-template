package template.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "aux_agrupacion")
public class Agrupacion extends EntidadPersistente{

    private String nombre;
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_fuerza")
    private TipoFuerza tipoFuerza;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;


}
