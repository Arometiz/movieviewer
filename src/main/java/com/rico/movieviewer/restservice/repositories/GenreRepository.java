package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
