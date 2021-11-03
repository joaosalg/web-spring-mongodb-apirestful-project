package com.restfulMongodb.workshopMongo.dto;

import com.restfulMongodb.workshopMongo.domain.User;

import java.io.Serializable;

// DTO É UM DESIGN PATTER QUE CRIA UM OBJ QUE CARREGA DADOS DO BANCO DE DADOS QUE VC DESEJAR //
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;

    public UserDTO(){
    }

    // PARA TER UMA FORMA AUTOMATIZADA DE INSTANCIAR UM USER DTO A PARTIR DE UM USER //
    public UserDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
