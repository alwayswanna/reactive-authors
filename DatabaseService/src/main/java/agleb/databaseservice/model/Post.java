package agleb.databaseservice.model;


import agleb.databaseservice.model.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "stories")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String full_story;
    private int likes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "author_info")
    private Account author;


    public static Post from(PostDTO postDTO){
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setFull_story(post.getFull_story());
        post.setLikes(postDTO.getLikes());
        if (Objects.nonNull(postDTO.getAuthorDTO())){
            post.setAuthor(Account.from(postDTO.getAuthorDTO()));
        }
        return post;
    }
}
