package com.rico.movieviewer.restservice.controllers;

import com.rico.movieviewer.restservice.mappings.ActorMappings;
import com.rico.movieviewer.restservice.repositories.ActorRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/actor")
@CrossOrigin
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @ResponseBody
    @GetMapping(value = ActorMappings.ACTOR_PICTURE, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByActorId(@RequestParam(value = "actor_id")String actor_id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/actorImages/" + actorRepository.findById(actor_id).get().getName().replaceAll("[\\.$|:]", "") + ".jpg");
        if(in == null){
            in = getClass()
                    .getResourceAsStream("/actorImages/Placeholder.jpg");
        }
        return IOUtils.toByteArray(in);
    }
}
