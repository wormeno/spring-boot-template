package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.model.Snic;
import template.repository.SnicRepository;

@Service
public class SnicService extends GenericEntityService<Snic, Long> {
    @Autowired
    private SnicRepository snicRepository;

    @Override
    protected JpaRepository<Snic, Long> getRepository() {
        return snicRepository;
    }


    @Override
    public void aplicarReglasDeNegocio(Snic snic){
        //- Validar permiso del usuario contra la seccional
        // Validar Consistencia de datos


    }

    private void validarConsistenciaDatosTiposDelitos(){


    }

    /*@Autowired
    private BarrioRepository barrioRepository;

    @Override
    protected GenericServiceEntity2<Barrio, Long> getService() {
        return null;
    }

    @Override
    protected JpaRepository<Barrio, Long> getRepository() {
        return barrioRepository;
    }*/
}
