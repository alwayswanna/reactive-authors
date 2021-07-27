package a.gleb.reactiverest.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Data
public class Account {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean active;
    private Set<AccountRole> roleDTO;
    private Collection<PostModel> postDTOList = new ArrayList<>();


}
