package com.restfulMongodb.workshopMongo.resources;

import com.restfulMongodb.workshopMongo.domain.Post;
import com.restfulMongodb.workshopMongo.domain.User;
import com.restfulMongodb.workshopMongo.dto.UserDTO;
import com.restfulMongodb.workshopMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// CLASSE RECURSO REST = REST CONTROLLER -- CAMINHO DO ENDPOINT = REQUEST MAP//
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    // PRA DIZER QUE O MÉTODO VAI SER UM ENDPOINT PARA O "/USERS" = @RequestMapping(method = RequestMethod.GET) ou @GetMapping //
    // RESPONSE ENTITY = RETORNA HTTP FORMATADA - .OK = INTANCIA O REPONSE ENTITY COM UM CÓDIGO DE RESPOSTA HTTP "COM SUCESSO" - .body = CONTÚDO //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        // PARA TRANSFORMAR A LISTA EM LISTA DTO - STREAM = TRANSFORMA A LISTA EM UMA COLEÇÃO - MAP = ATRIBUI UMA OPERAÇÃO EM CADA DADO DA STREAM - E POR FIM TRANSFORMA EM LISTDTO NOVAMENTE //
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(method = RequestMethod.POST)
    // OU @POSTMAPPING //
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto){
        User obj = userService.fromDTO(objDto);
        obj = userService.insert(obj);
        // PARA PEGAR O ENDEREÇO DO NOVO OBJ INSERIDO //
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        // REPOSTA VAZIA COM CABEÇALHO DO NOVO RECURSO CRIADO = BOA PRÁTICA //
        // CREATED RETORNA O CÓDIGO 201 = QUE É O CÓDIGO HTTP QUANDO SE CRIA UM NOVO RECURSO //
        return ResponseEntity.created(uri).build();
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteById(@PathVariable String id){
        userService.delete(id);
        // CÓDIGO 204 //
        return ResponseEntity.noContent().build();
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User obj = userService.fromDTO(objDto);
        // PARA GARANTIR O MESMO ID //
        obj.setId(id);
        obj = userService.update(obj);
        // PARA PEGAR O ENDEREÇO DO NOVO OBJ INSERIDO //
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        // REPOSTA VAZIA COM CABEÇALHO DO NOVO RECURSO CRIADO = BOA PRÁTICA //
        // CREATED RETORNA O CÓDIGO 201 = QUE É O CÓDIGO HTTP QUANDO SE CRIA UM NOVO RECURSO //
        return ResponseEntity.created(uri).build();
    }

    // @PathVariable = FAZ COM QUE O STRING ID "CASE" COM O ID DA URL //
    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
