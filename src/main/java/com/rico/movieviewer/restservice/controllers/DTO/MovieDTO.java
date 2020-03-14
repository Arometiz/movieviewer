package com.rico.movieviewer.restservice.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Date;

public class MovieDTO {

    @Getter
    private String movieName;

    @Getter
    private String description;

    @Getter
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    public MovieDTO(String movieName, String description, Date releaseDate){
        this.movieName = movieName;
        this.description = description;
        this.releaseDate  = releaseDate;
    }
}
