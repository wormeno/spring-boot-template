package template.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import template.exception.generic.ApiSubError;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorMessage {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    public ErrorMessage(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public  ErrorMessage(HttpStatus status, Throwable ex) {
     //   this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

}
