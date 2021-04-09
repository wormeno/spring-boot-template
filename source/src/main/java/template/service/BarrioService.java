package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.generic.service.GenericService;
import template.model.Barrio;
import template.repository.BarrioRepository;

@Service
public class BarrioService extends GenericService<Barrio, Long> {

    @Autowired
    private BarrioRepository barrioRepository;

//    @Override
//    protected GenericService<Barrio, Long> getService() {
//        return null;
//    }

/*    @Override
    protected JpaRepository<Barrio, Long> getRepository() {
        return barrioRepository;
    }*/
}
