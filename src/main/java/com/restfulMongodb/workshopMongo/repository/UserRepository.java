package com.restfulMongodb.workshopMongo.repository;

import com.restfulMongodb.workshopMongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// CLASSE RESPONSÁVEL PELO ACESSO AOS DADOS //
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
