package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
