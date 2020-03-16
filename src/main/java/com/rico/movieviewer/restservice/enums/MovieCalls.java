package com.rico.movieviewer.restservice.enums;


public enum MovieCalls {
    ALLAPPROVEDMOVIES {
        public String toString() {
            return "/all-approved-movies";
        }
    },

    ALLPENDINGMOVIES{
        @Override
        public String toString() {
            return "/all-pending-movies";
        }
    },

    SINGLEMOVIE{
        @Override
        public String toString() {
            return "/single-movie-data";
        }
    }
}
