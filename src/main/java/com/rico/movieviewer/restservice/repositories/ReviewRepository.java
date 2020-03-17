package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, String> {
}
