package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.controllers.json.JsonManager;
import com.rico.movieviewer.restservice.logic.jwt.JwtProvider;
import com.rico.movieviewer.restservice.mappings.MovieMappings;
import com.rico.movieviewer.restservice.repositories.UserRepository;
import com.rico.movieviewer.restservice.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JsonManager jsonManager;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(String username, String password) {
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                String token = jwtProvider.createToken(user.getUserId(), user.getUsername(), "ROLE_" + user.getRole());
                return new ResponseEntity<>(jsonManager.loginJson(token, MovieMappings.ALL_APPROVED_MOVIES, MovieMappings.UPLOAD_MOVIE,
                        MovieMappings.SINGLE_MOVIE_IMAGE), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(jsonManager.exceptionJson("Wrong username or password"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser() {
        return null;
    }
}