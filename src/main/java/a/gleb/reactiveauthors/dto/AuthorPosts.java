package a.gleb.reactiveauthors.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
@Data
@NoArgsConstructor
public class AuthorPosts {

    @Id
    private Long id;
    private String title;
    private String description;
    private String postText;
    private int like;
    private AuthorAccount authorAccount;

}
