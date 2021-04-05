package template.generic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntidadPersistente{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "create_date")
    private LocalDate createDate;

    @JsonIgnore
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateDate(LocalDate date) {
        this.createDate = date;
    }

    public void setLastModifiedDate(LocalDate date){
        this.lastModifiedDate = date;
    }

    public LocalDate getCreateDate(){
        return createDate;
    }

    public LocalDate getLastModifiedDate(){
        return lastModifiedDate;
    }

}
