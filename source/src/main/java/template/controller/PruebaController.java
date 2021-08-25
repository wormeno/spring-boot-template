package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import template.model.Prueba;
import template.model.User;
import template.repository.PruebaUserRepository;
import template.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PruebaController {

    private  PruebaUserRepository userRepository;

    @Autowired
    public PruebaController(PruebaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/prueba")
    public List<Prueba> getUsers() {
        return (List<Prueba>) userRepository.findAll();
    }

    @PostMapping("/prueba")
    ResponseEntity<String> addUser(@Valid @RequestBody Prueba prueba) {
        return ResponseEntity.ok("User is valid");
    }

 /*   @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }*/
}
