package service.rest.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import jdk.internal.instrumentation.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.rest.model.EntidadPersistente;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestException  extends ResponseEntityExceptionHandler  {
//    private Logger log;

/*

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<String>> handleException(WebExchangeBindException e) {
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
*/


    /**
     * Intercepta los errores de validación cuando se utiliza @Valid
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
      //  ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        String message = ex.getMessage();
        String error = "";
        if( ex.getMessage().contains("[\"")){
            String campo = ex.getMessage().substring(ex.getMessage().indexOf("[\""));
            error  = ex.getMessage().substring(ex.getMessage().indexOf("from")+5,ex.getMessage().indexOf(";"));
            error = error.concat(" from "+campo);
        }
        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, error, ex));
    }

    /**
     * Intercepta los errores de validación cuando se utiliza @Valid en conjunto con BindingResult
     * Se muestran los mensajes definidos en message de @NotNull
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        for(ConstraintViolation<?> error : ex.getConstraintViolations()) {
            details.add(error.getMessage());
        }

        return buildResponseEntity(new ErrorMessage(BAD_REQUEST,details.toString(), ex));
    }


    /**
     * Intercepta las Excepciones por violoación de integridad de datos ocurridos por diversos motivos.
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<Object> handleSQLException(SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
        String errorMessage = ex.getMessage().substring(ex.getMessage().indexOf("REFERENCES")+10);

        errorMessage = "Resource "+ errorMessage.substring(0,errorMessage.indexOf("(")).toUpperCase(Locale.ROOT) + " Not found";

        return buildResponseEntity(new ErrorMessage(BAD_REQUEST,errorMessage, ex));
    }

    //Custom Exception org.hibernate.exception.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
                                                            WebRequest request) {

        int i,j=0;
        HttpStatus codeResponse = INTERNAL_SERVER_ERROR;
        String  errorMessage = "unknow error";
        if( ex.getCause().getMessage().contains("Duplicate entry")){
            return buildResponseEntity(new ErrorMessage(BAD_REQUEST,"The resource exists!", ex));

        }

        errorMessage = ex.getCause().toString();
        i = errorMessage.indexOf("FOREIGN KEY (`id_");
        j = errorMessage.indexOf("`) REFERENCES");
        if( i!=0 && i+17 < j){
            errorMessage = "Resource "+ errorMessage.substring(i+17,j).toUpperCase(Locale.ROOT) + " Not found";
            codeResponse = BAD_REQUEST;
        }
        return buildResponseEntity(new ErrorMessage(codeResponse,errorMessage, ex));
    }


/*


    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        String message = ex.getMessage();

        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, ex.getMessage()));
    }

*/

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     */
    @ExceptionHandler(service.rest.exception.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, ex.getMessage(), ex));
    }

    /**
     * Intercepta los errores de validación cuando se utiliza @Valid
     * Se muestran los mensajes definidos en message de @NotNull
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, details.toString(), ex));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<Object> handleInvalidFormat(
            InvalidFormatException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request){

        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, ex.getMessage(), ex));

    }

    /**
     * Intecepta las Excepciones ocurridas excepciones ocurridos en las relaciones indicadas en el body.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> JpaObjectRetrievalFailureException(EntityNotFoundException ex) {
        String packageName = new EntidadPersistente().getClass().getPackage().getName();//GetNameEntity
        String error = ex.getMessage().replace(packageName+".","");
        error = error.replace("with id","");
        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(service.rest.exception.GenericException.class)
    private ResponseEntity<Object> GenericExceptionFailureException(GenericException ex) {
        return buildResponseEntity(new ErrorMessage(BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler(org.hibernate.exception.SQLGrammarException.class)
    private ResponseEntity<Object> PermissionExceptionFailure(
            org.hibernate.exception.SQLGrammarException ex ){

        return buildResponseEntity(new ErrorMessage(INTERNAL_SERVER_ERROR, ex.getCause().getMessage()));
    }
/*

    @ExceptionHandler(value = { ServletException.class })
    public ResponseEntity<Object> servletException(ServletException e) {
        String message = e.getMessage();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (message.equals("token_expired")) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            message = "the token is expired and not valid anymore";
        }
//        RestErrorResponse restErrorResponse = new RestErrorResponse(httpStatus, message);
//        return ResponseEntity.status(httpStatus).body(restErrorResponse);
        return buildResponseEntity(new ErrorMessage(INTERNAL_SERVER_ERROR, e.getCause().getMessage()));
    }
*/

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }


}
