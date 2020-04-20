package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Page<Movie> findAll(Pageable pageable);
    long countMovieByPending(boolean pending);
}
