package service.rest.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CuitValidator.class)
@Target({ TYPE, ANNOTATION_TYPE ,ElementType.METHOD, ElementType.FIELD })
@Retention(RUNTIME)
public @interface CuitConstraint {

    String message() default "Cuit no valido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
