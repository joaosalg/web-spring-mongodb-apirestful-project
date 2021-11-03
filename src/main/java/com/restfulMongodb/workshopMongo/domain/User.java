package com.restfulMongodb.workshopMongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// INDICA QUE ESSA CLASSE É UMA COLEÇÃO DO MONGODB //
@Document(collection = "user")
public class User implements Serializable {

    // PARA TRAFEGAR OS DADOS EM BYTES - ACREDITO QUE FICA MAIS LEVE //
    private static final long serialVersionUID = 1L;

    // CHAVE PRIMÁRIA DE IDENTIFICAÇÃO //
    @Id
    private String id;

    private String name;
    private String email;

    // BOA PRÁTICA INSTACIAR A LISTA //
    // LAZY = TRUE, SIGNIFICA QUE OS POSTS SÓ VÃO SER CARREGADOS SE OS POSTS FOREM ACESSADOS //
    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    public User(){
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
