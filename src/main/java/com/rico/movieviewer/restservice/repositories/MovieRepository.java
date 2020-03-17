package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
