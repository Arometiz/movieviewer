package com.rico.movieviewer.restservice.tables;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "star_number")
    private Integer starNumber;


    public String getReviewId() {
        return this.reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStarNumber() {
        return this.starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }
}
