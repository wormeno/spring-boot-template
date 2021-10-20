package service.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DtoPersistente implements Serializable {
    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private LocalDateTime createDate ;

    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
