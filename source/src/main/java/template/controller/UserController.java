package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import template.dto.UserDto;
import template.dto.UserMapper;
import template.generic.controller.RestGenericController;
import template.generic.service.GenericServiceEntity2;
import template.model.User;
import template.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/template/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class UserController extends RestGenericController<User,Long> {

    @Autowired
    private UserService userService;

    private UserMapper userMapper;

    @Override
    protected GenericServiceEntity2<User, Long> getService() {
        return userService;
    }



    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody UserDto userDto) {
        System.out.println("estamos aca");

//        User user = modelMapper.map(userDto,User.class);
        //return super.save();

        return null;
    }
}
