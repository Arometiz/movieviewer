package com.rico.movieviewer.restservice.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "review")
public class Review {
    @Getter @Setter
    @Id
    @Column(name = "review_id")
    private UUID reviewId;

    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "star_number")
    private Integer starNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
