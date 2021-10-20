package service.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import service.rest.dao.UserDao;
import service.rest.dto.AuthenticateUserDto;
import service.rest.dto.DtoPersistente;
import service.rest.dto.UserDto;
import service.rest.businessRule.ReglaDeNegocio;
import service.rest.exception.GenericException;
import service.rest.model.User;
import service.rest.repository.UserRepository;
import service.rest.service.generic.GenericDtoService;

import java.util.Optional;


@Service
public class UserService extends GenericDtoService<UserDto, User, Long, DtoPersistente> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    private ReglaDeNegocio userReglaDeNegocio ;


    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected ReglaDeNegocio<User> getBusinessRule(){
        return userReglaDeNegocio;
    }

    @Override
    public User mapperDtoToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        return user;
    }

    @Override
    public UserDto mapperEntityToDto(User user) {
        UserDto userDto = modelMapper.map(user,UserDto.class);

        return  userDto;
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsuario(username);
    }

    public Boolean usernameIsValid(String username){
        Optional<User> user = userRepository.findByUsuario(username);

        return user.isPresent() && !user.get().getBorrado() && user.get().getValidoCorreo() ;
    }

    /**
     * Retorna el usuario si el mismo es válido;
     *      No está borrado y Valido el correo
     * @param username
     * @return
     */
    public User getUserEnable(String username){
        Optional<User> user = userRepository.findByUsuario(username);

        if ( user.isPresent() && !user.get().getBorrado() && user.get().getValidoCorreo() )
            return user.get();
        else
            return null;
    }

    public void validateAuthenticate(AuthenticateUserDto authenticateUser) {
        String claveCandidata = DigestUtils.md5Hex(authenticateUser.getUsername()) + DigestUtils.md5Hex(authenticateUser.getPassword());

        if( !userRepository.existsByUsuarioAndClaveAndValidoCorreoIsTrueAndBorradoIsFalse(authenticateUser.getUsername(),claveCandidata) ){
            throw new GenericException("Username or password Not Found!");
        }

    }
}
