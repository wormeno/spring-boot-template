package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.model.Prueba;
import template.model.User;

public interface PruebaUserRepository extends JpaRepository<Prueba,Long> {
}
