package com.rico.movieviewer.restservice.controllers.DTO.POST;

import lombok.Getter;
import lombok.Setter;

public class LoginDTO {

    @Getter @Setter
    String username;

    @Getter @Setter
    String password;
}
