package com.restfulMongodb.workshopMongo.services.exception;

// RUNTIMEEXCEPTION POIS NÃO HÁ A NECESSIDADE DE TRATAR A EXCEÇÃO QUANDO O MÉTODO FOR CHAMADO //
public class ObjectNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    // SUPER = REPASSA PARA A SUPERCLASSE RUNTIMEEXCEPTION //
    public ObjectNotFoundException (String msg){
        super(msg);
    }
}
