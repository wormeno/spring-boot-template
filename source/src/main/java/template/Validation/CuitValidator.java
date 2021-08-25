package template.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static template.utils.UtilsTemplate.validateCuit;

public class CuitValidator implements ConstraintValidator<CuitConstraint, String> {

    @Override
    public void initialize(CuitConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cuit, ConstraintValidatorContext cvf) {
        try{
            validateCuit(cuit);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
