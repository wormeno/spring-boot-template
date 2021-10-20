package service.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import service.rest.dao.UserDao;
import service.rest.model.User;

import java.util.Optional;

@Service
public class AuthenticatedUser {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    public Long getId(){
        UserDetails userDetails = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> user = userService.findByUsername(userDetails.getUsername());
        Long id= userService.findByUsername(userDetails.getUsername()).get().getId();
        return id;
    }



}
