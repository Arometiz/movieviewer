package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {
}
