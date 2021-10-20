package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.model.Estado;
import template.repository.EstadoSnicRepository;

import java.util.Optional;

@Service
public class EstadoSnicService {

    @Autowired
    private EstadoSnicRepository estadoSnicRepository;


    protected JpaRepository<Estado, Long> getRepository() {
        return estadoSnicRepository;
    }

    public Estado getEstadoEnEdicion(){
        Optional<Estado> estado = estadoSnicRepository.findById(Long.valueOf(1));
        return estado.isPresent()? estado.get() : null;
    }

}
