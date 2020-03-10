package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.repositories.UserRepository;
import com.rico.movieviewer.restservice.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getdata", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserInformation(@RequestParam(name = "userId") String userID){
        User user = userRepository.findById(userID).get();
        return user;
    }
}
