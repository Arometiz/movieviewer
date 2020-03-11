package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
