package a.gleb.reactiverest.models;

import lombok.Data;

@Data
public class PlainPost {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
}
