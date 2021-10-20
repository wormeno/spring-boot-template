package service.rest.Validation;

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
@Constraint(validatedBy = YearValidator.class)
@Target({ TYPE, ANNOTATION_TYPE , ElementType.METHOD, ElementType.FIELD })
@Retention(RUNTIME)
public @interface YearConstraint {
    String message() default "Año no valido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
