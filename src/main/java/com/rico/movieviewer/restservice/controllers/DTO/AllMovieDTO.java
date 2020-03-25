package com.rico.movieviewer.restservice.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

public class AllMovieDTO {
    @Getter
    private String movie_id;

    @Getter
    private String name;

    @Getter
    @JsonFormat(pattern="yyyy")
    private Date releaseDate;

    public AllMovieDTO(String movie_id, String name, Date releaseDate){
        this.movie_id = movie_id;
        this.name = name;
        this.releaseDate = releaseDate;
    }
}