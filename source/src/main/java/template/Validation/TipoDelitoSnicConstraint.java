package template.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TipoDelitoSnicValidator.class)
@Target({ TYPE, ANNOTATION_TYPE , ElementType.METHOD, ElementType.FIELD })
@Retention(RUNTIME)
public @interface TipoDelitoSnicConstraint {

    String message() default "Tipo Delito Snic no valido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
