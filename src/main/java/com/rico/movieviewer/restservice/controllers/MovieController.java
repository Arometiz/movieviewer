package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.controllers.DTO.*;
import com.rico.movieviewer.restservice.logic.jwt.JwtProvider;
import com.rico.movieviewer.restservice.mappings.MovieMappings;
import com.rico.movieviewer.restservice.repositories.MovieRepository;
import com.rico.movieviewer.restservice.repositories.ReviewRepository;
import com.rico.movieviewer.restservice.repositories.UserRepository;
import com.rico.movieviewer.restservice.tables.Movie;
import com.rico.movieviewer.restservice.tables.Review;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping(value = MovieMappings.ALL_APPROVED_MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnMovieDTO getAllMovies(){
        ReturnMovieDTO returnMovieDTO = new ReturnMovieDTO();
        returnMovieDTO.getLinks().add(new LinkDTO("movie", MovieMappings.SINGLE_MOVIE_DATA));
        returnMovieDTO.getLinks().add(new LinkDTO("moviePicture", MovieMappings.SINGLE_MOVIE_IMAGE));
        movieRepository.findAll().forEach(movie -> {if (!movie.isPending()) returnMovieDTO.getMovies().add(
                new AllMovieDTO(movie.getMovieId(), movie.getName(), movie.getReleaseDate()));});
        return returnMovieDTO;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @GetMapping(value = MovieMappings.ALL_PENDING_MOVIES)
    public ReturnMovieDTO getAllPendingMovies(){
        ReturnMovieDTO returnMovieDTO = new ReturnMovieDTO();
        returnMovieDTO.getLinks().add(new LinkDTO("approveMovie", MovieMappings.APPROVE_UPLOADED_MOVIE));
        movieRepository.findAll().forEach(movie -> {if (movie.isPending())  returnMovieDTO.getMovies().add(
                new AllMovieDTO(movie.getMovieId(), movie.getName(), movie.getReleaseDate()));});
        return returnMovieDTO;
    }

    @GetMapping(value = MovieMappings.SINGLE_MOVIE_DATA)
    public Movie getMovieById(@RequestParam(value = "movie_id")String movie_id){
        return movieRepository.findById(movie_id).get();
    }

    @ResponseBody
    @GetMapping(value = MovieMappings.SINGLE_MOVIE_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByMovieId(@RequestParam(value = "movie_id")String movie_id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/images/" + movieRepository.findById(movie_id).get().getName().replace(":", "") + ".jpg");
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = MovieMappings.UPLOAD_MOVIE)
    public void uploadNewMovie(@RequestBody UploadMovieDTO uploadMovieDTO){
        Movie movie = new Movie();
        movie.setName(uploadMovieDTO.getMovieName());
        movie.setDescription(uploadMovieDTO.getDescription());
        movie.setReleaseDate(uploadMovieDTO.getReleaseDate());
        movie.setPending(true);
        movieRepository.save(movie);
    }

    @PostMapping(value = MovieMappings.APPROVE_UPLOADED_MOVIE)
    public void approvedUploadedMovie(@RequestParam(value = "movie_id")String movie_id){
        Movie movie = movieRepository.findById(movie_id).get();
        movie.setPending(false);
        movieRepository.save(movie);
    }

    @DeleteMapping(value = MovieMappings.DELETE_UPLOADED_MOVIE)
    public void deleteUploadedMovie(@RequestParam(value = "movie_id")String movie_id){
        movieRepository.delete(movieRepository.findById(movie_id).get());
    }

    @PostMapping(value = MovieMappings.ADD_NEW_REVIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewReview(@RequestBody ReviewDTO reviewDTO, HttpServletRequest req){
        Review review = new Review();
        try{
            review.setUser(userRepository.findById(jwtProvider.getUserIdFromToken(jwtProvider.resolveToken(req))).get());
            review.setComment(reviewDTO.getComment());
            review.setStarNumber(Integer.parseInt(reviewDTO.getStarNumber()));
            review.setMovie(movieRepository.findById(reviewDTO.getMovie_id()).get());
            reviewRepository.save(review);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}