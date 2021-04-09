package template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import template.generic.model.EntidadPersistente;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Data
@Entity(name = "users")
public class User extends EntidadPersistente {

    @ApiModelProperty(example = "25881112225", name = "cuil", value = "Ingrese el Cuil", required = true)
    private String cuil;

    @ApiModelProperty(example = "99", name = "idBarrio", value = "Ingrese el barrio", required = true)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio ;

    public User() {

    }
}
