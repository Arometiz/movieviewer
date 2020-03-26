package com.rico.movieviewer.restservice.controllers.DTO;

import com.rico.movieviewer.restservice.tables.Movie;
import com.rico.movieviewer.restservice.tables.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SingleMovieDTO {
    @Getter @Setter
    Movie movie;

    @Getter @Setter
    List<Review> reviews;

    public SingleMovieDTO(){
        movie = new Movie();
        reviews = new ArrayList<>();
    }
}
