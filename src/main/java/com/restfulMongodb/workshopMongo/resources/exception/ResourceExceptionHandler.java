package com.restfulMongodb.workshopMongo.resources.exception;

import com.restfulMongodb.workshopMongo.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

// @controlleradvice = INDICA QUE A CLASSE É RESPONSÁVEL POR TRATAR POSSÍVEIS ERROS NAS REQUISIÇÕES //
@ControllerAdvice
public class ResourceExceptionHandler {

    // QUANDO HÁ EXCEÇÃO DE ID NÃO ENCONTRADO //
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objNotFound(ObjectNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
