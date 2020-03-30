package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.controllers.DTO.ReviewDTO;
import com.rico.movieviewer.restservice.logic.jwt.JwtProvider;
import com.rico.movieviewer.restservice.mappings.MovieMappings;
import com.rico.movieviewer.restservice.repositories.MovieRepository;
import com.rico.movieviewer.restservice.repositories.ReviewRepository;
import com.rico.movieviewer.restservice.repositories.UserRepository;
import com.rico.movieviewer.restservice.tables.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "review")
public class ReviewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

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

    @PostMapping(value = MovieMappings.DELETE_REVIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteReview(@RequestParam(value = "review_id")String review_id){
        try{
            Review review = reviewRepository.findById(review_id).get();
            reviewRepository.delete(review);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
