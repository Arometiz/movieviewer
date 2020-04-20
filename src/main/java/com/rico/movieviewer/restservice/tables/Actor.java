package com.rico.movieviewer.restservice.tables;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @Column(name = "actor_id")
    @Getter @Setter
    private String actor_id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @Column(name = "role")
    @Getter @Setter
    private String role;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
