package a.gleb.reactiveauthors.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("subscription")
@Data
@NoArgsConstructor
public class UserSubscription {

    @Id
    private Long id;
    private AuthorAccount au_subscription;





}
