package a.gleb.reactiveauthors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPosts {

    @Id
    private Long id;
    private String title;
    private String description;
    private String postText;
    private String authorUserName;
    private Long likes;

    //TODO: edit authorUserName to normal entity One-to-Many relationships;
}
