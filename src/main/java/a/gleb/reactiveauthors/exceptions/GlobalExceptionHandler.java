package a.gleb.reactiveauthors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler
        public ResponseEntity<ExceptionData> handleException(NoSuchPostWithSelectedIdException noSuchPostWithSelectedIdException){
            ExceptionData exceptionData = new ExceptionData();
            exceptionData.setInfo("NoSuchPostWithSelectedIdException: there are no author post with selected [ID]-s");
            return new ResponseEntity<>(exceptionData, HttpStatus.BAD_REQUEST);
        }

}
