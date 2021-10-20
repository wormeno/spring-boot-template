package template.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "aux_tipo_delito_snic")
public class TipoDelitoSnic extends EntidadPersistente{

    private String codigo;
    private String nombre;
    private Float orden;
    private Boolean visible;
}
