package template.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import template.model.TipoDelitoSnic;
import template.repository.TipoDelitoSnicRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TipoDelitoSnicValidator implements ConstraintValidator<TipoDelitoSnicConstraint, String> {

    private List<TipoDelitoSnic> tiposDelitoSnic;

    @Autowired
    private TipoDelitoSnicRepository tipoDelitoSnicRepository;

    @Override
    public void initialize(TipoDelitoSnicConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String codigoTipoDelitoSnic, ConstraintValidatorContext cvf) {
        Integer tipoDelito = Integer.parseInt(codigoTipoDelitoSnic);
        tiposDelitoSnic = tipoDelitoSnicRepository.findTipoDelitoSnicByVisibleIsTrue();
        return tiposDelitoSnic.contains(tipoDelito);
    }
}
