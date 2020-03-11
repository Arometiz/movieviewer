package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.repositories.MovieRepository;
import com.rico.movieviewer.restservice.tables.Movie;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ServletContext servletContext;

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

    @GetMapping(value = "/single-movie-data")
    public Movie getMovieById(@RequestParam(value = "movie_id")String movie_id){
        return movieRepository.findById(movie_id).get();
    }

    @ResponseBody
    @GetMapping(value = "/single-movie-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByMovieId(@RequestParam(value = "movie_id")String movie_id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/images/" + movieRepository.findById(movie_id).get().getName() + ".jpg");
        return IOUtils.toByteArray(in);
    }
}