package a.gleb.reactiverest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DatabaseResponseException extends RuntimeException{

    @Getter
    @Setter
    String message;

}
