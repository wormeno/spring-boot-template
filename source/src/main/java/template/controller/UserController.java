package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import template.dto.UserDto;
import template.model.User;
import template.service.GenericDtoService;

@RestController
@RequestMapping("/template/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class UserController extends GenericDtoController<UserDto,User,Long> {

    @Autowired
    private GenericDtoService userService;

    protected GenericDtoService<UserDto,User, Long> getService() {
        return userService;
    }

}
