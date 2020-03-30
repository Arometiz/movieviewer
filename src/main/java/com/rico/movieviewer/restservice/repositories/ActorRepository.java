package com.rico.movieviewer.restservice.repositories;

import com.rico.movieviewer.restservice.tables.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, String> {
}
