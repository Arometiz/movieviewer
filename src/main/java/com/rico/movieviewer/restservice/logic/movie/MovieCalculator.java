package com.rico.movieviewer.restservice.logic.movie;

import com.rico.movieviewer.restservice.repositories.ReviewRepository;
import com.rico.movieviewer.restservice.tables.Movie;
import com.rico.movieviewer.restservice.tables.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MovieCalculator {

    public double calculateMovieStar(Movie movie, ReviewRepository repo){
        if(repo.countAllByMovie(movie) == 0){
            return 0;
        }
        long reviewCount = repo.countAllByMovie(movie);
        long totalNumber = 0;
        for(Review review : repo.findByMovie(movie)) {
            totalNumber += review.getStarNumber();
        }
        BigDecimal bd = new BigDecimal((double)totalNumber/reviewCount).setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
