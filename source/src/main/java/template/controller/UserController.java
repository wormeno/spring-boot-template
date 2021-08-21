package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.*;
import template.dto.UserDto;
import template.model.User;
import template.service.GenericDtoService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/template/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class UserController extends GenericDtoController<UserDto,User,Long> {

    @Autowired
    private GenericDtoService userService;


    protected GenericDtoService<UserDto,User, Long> getService() {
        return userService;
    }


  /*  @Override
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody UserDto usuario){
        return super.update(id,usuario);
    }*/

/*    protected GenericDtoService<UserDto, Long> getService() {
        return userService;
    }*/

/*
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<BarrioDto> find(@PathVariable Long id){
        Optional<Barrio> entity = barrioService.findById(id);

        if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
//        BarrioMapper barrioMapper = Mappers.getMapper(BarrioMapper.class);
//        BarrioDto barrioDto = barrioMapper.toBarrioDto(entity.get());
        DozerBeanMapper mapper =  new DozerBeanMapper();
        BarrioDto barrioDto = mapper.map(entity.get(),BarrioDto.class);
        return ResponseEntity.ok(barrioDto) ;
    }*/

/*

    @Override
    protected GenericServiceEntity2<User, Long> getService() {
        return userService;
    }
*/


/*

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        System.out.println("estamos aca");

//        User user = modelMapper.map(userDto,User.class);
        //return super.save();

        return null;
    }
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));

    return errors;
}

}
