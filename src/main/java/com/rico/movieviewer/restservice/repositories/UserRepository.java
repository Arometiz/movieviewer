package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
