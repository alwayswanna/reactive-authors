package a.gleb.reactiverest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DatabaseServerException extends RuntimeException {

    @Getter
    @Setter
    String message;

}
