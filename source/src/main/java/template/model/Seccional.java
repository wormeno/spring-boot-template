package template.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "aux_seccional")
public class Seccional extends EntidadPersistente{

    private String nombre;
    private String codigo;

    @Column(name = "todas_las_seccionales")
    private Boolean todasLasSeccionales;

    @ManyToOne
    @JoinColumn(name = "id_agrupacion")
    private Agrupacion agrupacion;

}
