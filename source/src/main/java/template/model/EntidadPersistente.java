package template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public  class EntidadPersistente{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "fecha_creacion")
    private LocalDateTime createDate;

    @JsonIgnore
    @Column(name = "fecha_ultima_modificacion")
    private LocalDateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateDate(LocalDateTime date) {
        this.createDate = date;
    }

    public void setLastModifiedDate(LocalDateTime date){
        this.lastModifiedDate = date;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }

    public LocalDateTime getLastModifiedDate(){
        return lastModifiedDate;
    }

}
