package com.rico.movieviewer.restservice.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Getter @Setter
    @Column(name = "movie_id")
    private String movieId;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Column(name = "movie_release")
    private java.sql.Date movieRelease;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    @Getter @Setter
    @Column(name = "pending")
    private boolean pending;

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews;
}
