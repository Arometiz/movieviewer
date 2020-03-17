package com.rico.movieviewer.restservice.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

public class LinkDTO {

    @Getter @Setter
    String name;
    @Getter @Setter
    String link;

    public LinkDTO(String name, String link){
        this.name = name;
        this.link = link;
    }
}
