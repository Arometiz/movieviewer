package com.rico.movieviewer.restservice.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rico.movieviewer.restservice.tables.Genre;
import lombok.Getter;

import java.util.Date;
import java.util.List;


public class UploadMovieDTO {

    @Getter
    private String movieName;

    @Getter
    private String description;

    @Getter
    private String releaseDate;

    @Getter
    private String youtube_id;

    @Getter
    private List<Genre> genres;

    public UploadMovieDTO(String movieName, String description, String releaseDate, String youtube_id, List<Genre> genres){
        this.movieName = movieName;
        this.description = description;
        this.releaseDate  = releaseDate;
        this.youtube_id = youtube_id;
        this.genres = genres;
    }
}
