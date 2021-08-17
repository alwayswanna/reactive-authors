package agleb.databaseservice.exceptions.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class IncorrectData {

    @Getter
    @Setter
    private String message;

}
