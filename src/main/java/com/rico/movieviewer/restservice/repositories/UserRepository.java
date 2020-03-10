package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, String> {
}
