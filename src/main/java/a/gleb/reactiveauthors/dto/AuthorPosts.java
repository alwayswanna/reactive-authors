package a.gleb.reactiveauthors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "postText")
    private String postText;
    @Column(name = "like")
    private int like;

    @Override
    public String toString() {
        return "AuthorPosts{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postText='" + postText + '\'' +
                ", like=" + like +
                '}';
    }
}
