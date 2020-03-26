package com.rico.movieviewer.restservice.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rico.movieviewer.restservice.tables.Genre;
import lombok.Getter;

import java.util.*;

public class AllMovieDTO {
    @Getter
    private String movie_id;

    @Getter
    private String name;

    @Getter
    @JsonFormat(pattern = "yyyy")
    private Date releaseDate;

    @Getter
    private List<Genre> genres;

    public AllMovieDTO(String movie_id, String name, Date releaseDate, List<Genre> genres) {
        this.movie_id = movie_id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genres = genres;

    }
}