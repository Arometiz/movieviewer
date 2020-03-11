package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.repositories.MovieRepository;
import com.rico.movieviewer.restservice.tables.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(value = "/all-approved-movies")
    public List<Movie> getAllMovies(){
        ArrayList<Movie> approvedMovies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> {if (!movie.isPending()) approvedMovies.add(movie);});
        return approvedMovies;
    }

    @GetMapping(value = "/all-pending-movies")
    public List<Movie> getAllPendingMovies(){
        ArrayList<Movie> pendingMovies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> {if (movie.isPending()) pendingMovies.add(movie);});
        return pendingMovies;
    }

    @GetMapping(value = "/single-movie")
    public Movie getMovieById(@RequestParam(value = "movie_id")String movie_id){
        return movieRepository.findById(movie_id).get();
    }
}