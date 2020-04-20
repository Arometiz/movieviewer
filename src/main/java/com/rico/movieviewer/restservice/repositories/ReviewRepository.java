package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Movie;
import com.rico.movieviewer.restservice.tables.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByMovie(Movie movie);
    long countAllByMovie(Movie movie);
}
