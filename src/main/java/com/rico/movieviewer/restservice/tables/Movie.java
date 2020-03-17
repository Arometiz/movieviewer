package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Getter @Setter
    @Column(name = "movie_id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
            strategy = "uuid")
    private String movieId;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "movie_release")
    private Date releaseDate;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    @Getter @Setter
    @Column(name = "pending")
    private boolean pending;

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews;
}
