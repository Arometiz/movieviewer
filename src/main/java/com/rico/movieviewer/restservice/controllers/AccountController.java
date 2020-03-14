package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.logic.jwt.JwtProvider;
import com.rico.movieviewer.restservice.repositories.UserRepository;
import com.rico.movieviewer.restservice.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(String username, String password){
        for(User user : userRepository.findAll()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                String rolePrefix = "ROLE_";
                return jwtProvider.createToken(user.getUserId(), user.getUsername(), rolePrefix + user.getRole());
            }
        }
        return "Wrong username or password";
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(){
        return null;
    }
}