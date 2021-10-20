package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.model.TipoDelitoSnic;

import java.util.List;

public interface TipoDelitoSnicRepository extends JpaRepository<TipoDelitoSnic,Long> {

    public List<TipoDelitoSnic> findTipoDelitoSnicByVisibleIsTrue();

}
