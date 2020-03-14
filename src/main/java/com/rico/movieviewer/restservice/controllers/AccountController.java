package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AccountController {
    private static String rolePrefix = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(String username, String password){
        String token = null;
        userRepository.findAll().forEach(user -> {if(user.getUsername().equals(username) && user.getPassword().equals(password)) /* create token */ });
        return token;
    }
}
