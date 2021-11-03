package com.restfulMongodb.workshopMongo.services;

import com.restfulMongodb.workshopMongo.domain.Post;
import com.restfulMongodb.workshopMongo.repository.PostRepository;
import com.restfulMongodb.workshopMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// @SERVICE PARA DIZER AO SPRING SERÁ UM SERVIÇO INJETÁVEL EM OUTRAS CLASSES //
@Service
public class PostService {

    // PARA QUE A CLASSE SERVICE CONVERSE COM A REPOSITORY //
    // AUTOWIRED = INTANCIA O OBJ AUTOMATICAMENTE //
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> findById(String id){
        Optional<Post> obj = postRepository.findById(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return obj;
    }

    public List<Post> findByTitle(String text){
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch (String text, Date minDate, Date maxDate){
        // CONVERTER DOS MILISEGUNDOS //
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
