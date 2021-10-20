package service.rest.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rest.dto.AuthenticateUserDto;
import service.rest.security.jwt.JwtToken;
import service.rest.service.UserService;

import javax.validation.Valid;

@Api(value = "Authenticate", description = "REST API for Authenticate", tags = { "AuthenticateUser" })
@RestController
@RequestMapping("/users/autenticate")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class AuthenticateController {

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserService userService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public  ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateUserDto authenticateUser){
        userService.validateAuthenticate(authenticateUser);
        String token = generateToken(authenticateUser.getUsername());
        return ResponseEntity.ok(token);
    }

    private String generateToken(String username){
        return jwtToken.generateToken(username,1L);
    }
}
