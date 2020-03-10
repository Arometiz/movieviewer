package com.rico.movieviewer.restservice.tables;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "movie_id")
    private String movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "movie_release")
    private java.sql.Date movieRelease;

    @Column(name = "description")
    private String description;


    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getMovieRelease() {
        return this.movieRelease;
    }

    public void setMovieRelease(java.sql.Date movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
