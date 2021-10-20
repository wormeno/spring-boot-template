package template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import template.dto.UserDto;
import template.model.User;
import template.repository.UserRepository;


@Service
public class UserService extends GenericDtoService<UserDto, User, Long> {
    @Autowired
    private UserRepository userRepository;


    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User mapperDtoToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
     /*   Optional<Barrio> barrio = barrioRepository.findById(user.getBarrio().getId());
        user.setBarrio(barrio.get());*/
        return user;
    }

    @Override
    public UserDto mapperEntityToDto(User user) {
        UserDto userDto = modelMapper.map(user,UserDto.class);

        return  userDto;
    }













/*

    @Override
    protected GenericServiceEntity2<User, Long> getService() {
        return null;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
*/


}
