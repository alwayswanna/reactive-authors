package a.gleb.reactiverest.models;

import lombok.Data;

@Data
public class PostModel {

    private Long id;
    private String title;
    private String description;
    private String full_story;
    private int likes;
    private Account authorDTO;

}
