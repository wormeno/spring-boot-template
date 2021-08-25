package template.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Pattern( regexp = "[0-9]*", message = "Debe ser un numero")
    private long id;
    @NotNull
    @Size(min = 2, message = "Blog Title must have at least 2 characters")
    private String blogTitle;
    @NotBlank(message = "Blog Editor cannot be blank")
    @Pattern(regexp = "^[a-zA-z]*$", message = "Debe ingresar un string")
    private String blogEditor;
    @Email(message = "Email should be valido")
    private String blogEmail;

    @NotNull
    @Min(value = 1, message = "Required min age is 10")
    @Max(value = 5, message = "Required max age is 50")
    private Integer direccion;
}
