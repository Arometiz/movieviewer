package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
    @JsonFormat(pattern="yyyy")
    @Column(name = "movie_release")
    private Date releaseDate;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    @Getter @Setter
    @Column(name = "youtube_id")
    private String youtube_id;

    @Getter @Setter
    @Column(name = "pending")
    private boolean pending;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id"))
    private List<Genre> genres;
}
