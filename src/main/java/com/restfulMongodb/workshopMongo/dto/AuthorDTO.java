package com.restfulMongodb.workshopMongo.dto;

import com.restfulMongodb.workshopMongo.domain.User;

import java.io.Serializable;

// DTO É UM DESIGN PATTER QUE CRIA UM OBJ QUE CARREGA DADOS DO BANCO DE DADOS QUE VC DESEJAR //
public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO(){
    }

    public AuthorDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
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
}
