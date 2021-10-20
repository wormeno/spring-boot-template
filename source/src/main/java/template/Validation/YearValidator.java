package template.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;


public class YearValidator implements ConstraintValidator<YearConstraint, String> {

    @Override
    public void initialize(YearConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String anio, ConstraintValidatorContext cvf) {
        Integer currentYear = LocalDate.now().getYear();
        Integer elAnio;
        try{
            elAnio = Integer.parseInt(anio);
        }
        catch (Exception e){
            return false;
        }

        return elAnio >=2013 && elAnio <= currentYear;
    }
}
