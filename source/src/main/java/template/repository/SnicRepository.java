package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.model.Snic;

public interface SnicRepository extends JpaRepository<Snic,Long> {

}
