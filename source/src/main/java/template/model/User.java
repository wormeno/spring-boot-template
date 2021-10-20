package template.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
@Entity(name = "sys_usuario")
public class User  extends EntidadPersistente {


    @Email
    private String correo;

    @Column(name = "valido_correo")
    private Boolean validoCorreo;

    private String usuario;

    @JsonIgnore
    private String clave;

    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean borrado;

    @JsonIgnore
    @Column(name = "intento_login_fallido")
    private Integer intentosLoginFallido;

    @JsonIgnore
    @Column(name = "fecha_ultimo_intento_fallido")
    private LocalDateTime fechaUltimoIntentoFallido;

    @JsonIgnore
    @Column(name = "clave_valida_hasta")
    private LocalDateTime claveValidaHasta;
/*
    @JsonIgnore
    @Column(name = "ultimo_acceso")
    private Boolean ultimoAcceso;

    @JsonIgnore
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;*/

 /*   @NonNull
    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio ;*/

    public User() { }

}
