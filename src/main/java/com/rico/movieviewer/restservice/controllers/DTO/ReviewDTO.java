package com.rico.movieviewer.restservice.controllers.DTO;

import lombok.Getter;

public class ReviewDTO {

    @Getter
    String comment;

    @Getter
    String starNumber;

    @Getter
    String movie_id;
}
