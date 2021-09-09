package agleb.databaseservice.model.dto;

import agleb.databaseservice.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String full_story;
    private int likes;
    private AccountDTO authorDTO;

    public static PostDTO from(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setFull_story(post.getFull_story());
        postDTO.setLikes(post.getLikes());
        if (Objects.nonNull(post.getAuthor())){
            postDTO.setAuthorDTO(AccountDTO.from(post.getAuthor()));
        }
        return postDTO;
    }
}
