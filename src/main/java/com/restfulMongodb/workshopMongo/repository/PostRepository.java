package com.restfulMongodb.workshopMongo.repository;

import com.restfulMongodb.workshopMongo.domain.Post;
import com.restfulMongodb.workshopMongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// CLASSE RESPONSÁVEL PELO ACESSO AOS DADOS //
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // @Query PERMITE ENTRAR NA CONSULTA DO MONGODB NA FORMA DE TEXTO JSON - ?0 = PRIMEIRO PARAMETRO QUE VIER NO MÉTODO //
    @Query("{ 'title': { $regex: ?0 , $options: 'i' } }")
    List<Post> searchTitle(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2}, { $or: [ {'title': { $regex: ?0 , $options: 'i'}, {'body': { $regex: ?0 , $options: 'i'}, {'comments.text': { $regex: ?0 , $options: 'i'} ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
