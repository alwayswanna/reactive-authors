package a.gleb.reactiveauthors.exceptions;

public class NoSuchPostWithSelectedIdException extends RuntimeException{

    public NoSuchPostWithSelectedIdException(String message){
        super(message);
    }
}
