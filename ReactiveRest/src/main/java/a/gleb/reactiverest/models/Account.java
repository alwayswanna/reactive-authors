package a.gleb.reactiverest.models;

import lombok.Data;

import java.util.List;

@Data
public class Account {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean active;
    List<PostModel> postModels;


}
