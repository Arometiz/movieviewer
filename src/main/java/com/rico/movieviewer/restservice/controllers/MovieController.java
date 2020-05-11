package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.controllers.DTO.*;
import com.rico.movieviewer.restservice.logic.movie.MovieCalculator;
import com.rico.movieviewer.restservice.mappings.MovieEndpoints;
import com.rico.movieviewer.restservice.repositories.MovieRepository;
import com.rico.movieviewer.restservice.repositories.ReviewRepository;
import com.rico.movieviewer.restservice.tables.Movie;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping(value = MovieEndpoints.ALL_APPROVED_MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnMovieDTO getAllMovies(@RequestParam(value = "page") int page){
        Pageable pageable = PageRequest.of(page, 16);
        ReturnMovieDTO returnMovieDTO = new ReturnMovieDTO();
        returnMovieDTO.getLinks().add(new LinkDTO("movie", MovieEndpoints.SINGLE_MOVIE_DATA));
        returnMovieDTO.getLinks().add(new LinkDTO("moviePicture", MovieEndpoints.SINGLE_MOVIE_IMAGE));
        movieRepository.findAll(pageable).forEach(movie -> {if (!movie.isPending()) returnMovieDTO.getMovies().add(
                new AllMovieDTO(movie.getMovieId(), movie.getName(), movie.getReleaseDate(), movie.getGenres()));});
        returnMovieDTO.setTotalMovieCount(movieRepository.countMovieByPending(false));
        return returnMovieDTO;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @GetMapping(value = MovieEndpoints.ALL_PENDING_MOVIES)
    public ReturnMovieDTO getAllPendingMovies(@RequestParam(value = "page") int page){
        Pageable pageable = PageRequest.of(page, 16);
        ReturnMovieDTO returnMovieDTO = new ReturnMovieDTO();
        returnMovieDTO.getLinks().add(new LinkDTO("approveMovie", MovieEndpoints.APPROVE_UPLOADED_MOVIE));
        movieRepository.findAll(pageable).forEach(movie -> {if (movie.isPending())  returnMovieDTO.getMovies().add(
                new AllMovieDTO(movie.getMovieId(), movie.getName(), movie.getReleaseDate(), movie.getGenres()));});
        returnMovieDTO.setTotalMovieCount(movieRepository.countMovieByPending(true));
        return returnMovieDTO;
    }

    @GetMapping(value = MovieEndpoints.SINGLE_MOVIE_DATA)
    public SingleMovieDTO getMovieById(@RequestParam(value = "movie_id")String movie_id){
        MovieCalculator calculator = new MovieCalculator();
        SingleMovieDTO dto = new SingleMovieDTO();
        Movie movie =  movieRepository.findById(movie_id).get();
        dto.setReviews(reviewRepository.findByMovie(movie));
        dto.setMovieRating(calculator.calculateMovieStar(movie, reviewRepository));
        dto.setMovie(movie);
        return dto;
    }

    @ResponseBody
    @GetMapping(value = MovieEndpoints.SINGLE_MOVIE_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByMovieId(@RequestParam(value = "movie_id")String movie_id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/images/" + movieRepository.findById(movie_id).get().getName().replace(":", "") + ".jpg");
        if(in == null){
            in = getClass()
                    .getResourceAsStream("/images/Placeholder.jpg");
        }
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = MovieEndpoints.UPLOAD_MOVIE)
    public void uploadNewMovie(@RequestBody UploadMovieDTO uploadMovieDTO){
        Movie movie = new Movie();
        movie.setName(uploadMovieDTO.getMovieName());
        movie.setDescription(uploadMovieDTO.getDescription());
        movie.setReleaseDate(uploadMovieDTO.getReleaseDate());
        movie.setGenres(uploadMovieDTO.getGenres());
        movie.setYoutube_id(uploadMovieDTO.getYoutube_id());
        movie.setPending(true);
        movieRepository.save(movie);
    }

    @PostMapping(value = MovieEndpoints.APPROVE_UPLOADED_MOVIE)
    public void approvedUploadedMovie(@RequestParam(value = "movie_id")String movie_id){
        Movie movie = movieRepository.findById(movie_id).get();
        movie.setPending(false);
        movieRepository.save(movie);
    }

    @DeleteMapping(value = MovieEndpoints.DELETE_UPLOADED_MOVIE)
    public void deleteUploadedMovie(@RequestParam(value = "movie_id")String movie_id){
        movieRepository.delete(movieRepository.findById(movie_id).get());
    }
}