package com.rico.movieviewer.restservice.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "genre")
public class Genre{

    @Id
    @Getter @Setter
    @Column(name = "genre_id")
    private int id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}
