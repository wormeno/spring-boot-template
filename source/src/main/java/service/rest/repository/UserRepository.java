package service.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.rest.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByUsuario(String username);

    public Boolean existsByUsuarioAndClaveAndValidoCorreoIsTrueAndBorradoIsFalse(String username,String clave);
}
