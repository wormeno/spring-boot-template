package service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.rest.controller.generic.GenericDtoController;
import service.rest.dto.DtoPersistente;
import service.rest.dto.UserDto;
import service.rest.model.User;
import service.rest.service.generic.GenericDtoService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class UserController extends GenericDtoController<UserDto,User,Long> {

    @Autowired
    private GenericDtoService userService;

    protected GenericDtoService<UserDto,User, Long, DtoPersistente> getService() {
        return userService;
    }


}
