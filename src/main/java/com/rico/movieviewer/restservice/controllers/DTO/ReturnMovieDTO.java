package com.rico.movieviewer.restservice.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ReturnMovieDTO {

    @Getter @Setter
    public List<AllMovieDTO> movies;

    @Getter @Setter
    public List<LinkDTO> links;

    @Getter @Setter
    private long totalMovieCount;

    public ReturnMovieDTO(){
        movies = new ArrayList<>();
        links = new ArrayList<>();
    }
}
