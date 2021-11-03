package com.restfulMongodb.workshopMongo.resources;

import com.restfulMongodb.workshopMongo.domain.Post;
import com.restfulMongodb.workshopMongo.resources.util.URL;
import com.restfulMongodb.workshopMongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// CLASSE RECURSO REST = REST CONTROLLER -- CAMINHO DO ENDPOINT = REQUEST MAP//
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Post>> findById(@PathVariable String id){
        Optional<Post> obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        // DECODIFICAR //
        text = URL.decodeParam(text);

        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
                                                 @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        // DECODIFICAR //
        text = URL.decodeParam(text);
        //TRATAR DATA MINIMA //
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
