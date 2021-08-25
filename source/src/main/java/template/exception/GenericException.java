package template.exception;

public class GenericException extends RuntimeException{

    public GenericException() {
        super();
    }

    public GenericException(String message) {
        super(message);
    }
}
