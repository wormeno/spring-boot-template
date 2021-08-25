package template.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "users")
public class User extends EntidadPersistente {

    @ApiModelProperty(example = "25881112225", name = "cuil", value = "Ingrese el Cuil", required = true)
    private String cuil;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio ;

    public User() {

    }
}
