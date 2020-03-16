package com.rico.movieviewer.restservice.controllers.json;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonManager {

    private Map<Object, Object> jsonObject;

    public Map<Object, Object> loginJson(String token, String allMoviesLink, String singleMovieLink){
        initJsonObject();
        jsonObject.put("token", token);
        jsonObject.put("all-movies", allMoviesLink);
        jsonObject.put("single-movie", singleMovieLink);
        return jsonObject;
    }

    public Map<Object, Object> exceptionJson(String message){
        initJsonObject();
        jsonObject.put("exception", message);
        return jsonObject;
    }

    private void initJsonObject(){
        jsonObject = new HashMap<>();
    }
}
