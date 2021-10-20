package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.model.Estado;

import java.util.Optional;

public interface EstadoSnicRepository extends JpaRepository<Estado,Long> {


}
