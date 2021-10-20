package template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity(name = "snic")
@Table( uniqueConstraints = {@UniqueConstraint(columnNames = {"id_departamento","id_seccional","anio","id_mes"})})
public class Snic extends EntidadPersistente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "id_seccional")
    private Seccional seccional;

    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "id_mes")
    private Mes mes;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @Column(name = "registra_delitos")
    private Boolean registraDelitos;



    @ManyToOne
    @JoinColumn(name = "id_usuario_consolida")
    private User usuarioConsolidante;

    @JsonIgnore
    @Column(name = "fecha_consolidacion")
    private LocalDateTime fechaConsolidacion;

    private String Lote;

    @JsonIgnore
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @OneToMany
    @JoinColumn(name = "id_snic")
    Collection<SnicDelito> snicDelitos = new ArrayList<>();
}
