package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;
import template.dto.UserDto;
import template.dto.UserMapper;
import template.generic.controller.RestGenericController;
import template.generic.service.GenericService;
import template.model.User;
import template.service.UserService;

import javax.persistence.EntityGraph;
import javax.validation.Valid;

@RestController
@RequestMapping("/template/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class UserController extends RestGenericController<User,Long> {

    @Autowired
    private UserService userService;

    private UserMapper userMapper;

    @Override
    protected GenericService<User, Long> getService() {
        return userService;
    }



    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save1(@Valid @RequestBody EntityGraph userEntityGraph) {
        System.out.println("estamos aca");

//        User user = modelMapper.map(userDto,User.class);
        //return super.save();

        return null;
    }
}
