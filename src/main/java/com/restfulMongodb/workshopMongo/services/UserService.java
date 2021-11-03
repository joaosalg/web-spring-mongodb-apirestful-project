package com.restfulMongodb.workshopMongo.services;

import com.restfulMongodb.workshopMongo.domain.User;
import com.restfulMongodb.workshopMongo.dto.UserDTO;
import com.restfulMongodb.workshopMongo.repository.UserRepository;
import com.restfulMongodb.workshopMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @SERVICE PARA DIZER AO SPRING SERÁ UM SERVIÇO INJETÁVEL EM OUTRAS CLASSES //
@Service
public class UserService {

    // PARA QUE A CLASSE SERVICE CONVERSE COM A REPOSITORY //
    // AUTOWIRED = INTANCIA O OBJ AUTOMATICAMENTE //
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User entity) {
        User newObj = userRepository.findById(entity.getId()).get();
        updateData(newObj, entity);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
