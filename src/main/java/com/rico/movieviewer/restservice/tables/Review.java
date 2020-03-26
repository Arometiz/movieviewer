package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "review")
public class Review {

    @Id
    @Getter @Setter
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
            strategy = "uuid")
    @Column(name = "review_id")
    private String reviewId;

    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "star_number")
    private int starNumber;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
