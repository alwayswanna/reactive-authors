package a.gleb.reactiveauthors.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class AdministrativeAccount {
    @Id
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String password;

}
