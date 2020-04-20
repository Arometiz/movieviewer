package com.rico.movieviewer.restservice.controllers.json;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonManager {

    private Map<Object, Object> map;

    public Map<Object, Object> loginJson(String token, String allMoviesLink, String uploadMovieLink, String moviePicture){
        initJsonObject();
        map.put("token", token);
        map.put("all-movies", allMoviesLink);
        map.put("upload-movie", uploadMovieLink);
        map.put("movie-image", moviePicture);
        return map;
    }

    public Map<Object, Object> exceptionJson(String message){
        initJsonObject();
        map.put("exception", message);
        return map;
    }

    private void initJsonObject(){
        map = new HashMap<>();
    }
}
