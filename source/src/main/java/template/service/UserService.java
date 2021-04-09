package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.generic.service.GenericService;
import template.model.User;
import template.repository.UserRepository;

@Service
public class UserService extends GenericService<User, Long> {
    @Autowired
    private UserRepository userRepository;

    /*@Override
    protected GenericService<User, Long> getService() {
        return null;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }*/


}
