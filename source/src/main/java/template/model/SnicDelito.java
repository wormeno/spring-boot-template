package template.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "snic_delito")
public class SnicDelito extends EntidadPersistente{

    @ManyToOne
    @JoinColumn(name = "id_tipo_Delito_snic")
    private TipoDelitoSnic tipoDelitoSnic;

    @Column(name = "por_denuncia_particular")
    private Integer porDenunciaParticular;

    @Column(name = "por_intervencion_policial")
    private Integer porIntervencionPolicial;

    @Column(name = "por_orden_judicial")
    private Integer porOrdenJudicial;

    @Column(name = "otro_no_consta")
    private Integer otroNoConsta;

    @Column(name = "victimas_masculino")
    private Integer victimasMasculino;

    @Column(name = "victimas_femenino")
    private Integer victimasFemenino;

    @Column(name = "victimas_no_consta")
    private Integer victimasNoConsta;

    private Integer varon;
    private Integer mujer;

    @Column(name = "varon_trans")
    private Integer varonTrans;

    @Column(name = "mujer_trans")
    private Integer mujerTrans;

    @Column(name = "genero_otro")
    private Integer generoOtro;

    @Column(name = "genero_sd")
    private Integer generoSd;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
}
