package agleb.databaseservice.exceptions.advice;

import agleb.databaseservice.exceptions.DuplicateUsernameException;
import agleb.databaseservice.exceptions.NoSuchAccountException;
import agleb.databaseservice.exceptions.NoSuchPostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalApiExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(DuplicateUsernameException exception){
        return new ResponseEntity<>(new IncorrectData(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchAccountException exception){
        return new ResponseEntity<>(new IncorrectData(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchPostException exception){
        return new ResponseEntity<>(new IncorrectData(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
