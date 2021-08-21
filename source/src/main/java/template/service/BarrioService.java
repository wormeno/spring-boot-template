package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.model.Barrio;
import template.repository.BarrioRepository;

import java.util.Optional;

@Service
public class BarrioService extends GenericEntityService<Barrio, Long> {
    @Autowired
    private BarrioRepository barrioRepository;

    @Override
    protected JpaRepository<Barrio, Long> getRepository() {
        return barrioRepository;
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
