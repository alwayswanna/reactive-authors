package agleb.databaseservice.model;

import agleb.databaseservice.model.dto.AccountDTO;
import agleb.databaseservice.model.dto.PostDTO;
import agleb.databaseservice.model.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean active;


    @OneToMany(mappedBy = "author", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private Collection<Post> user_stories;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Role roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public static Account from(AccountDTO accountDTO){
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setName(accountDTO.getName());
        account.setSurname(accountDTO.getSurname());
        account.setEmail(accountDTO.getEmail());
        account.setActive(accountDTO.isActive());
        account.setUser_stories(converterList(accountDTO.getPostDTOList()));
        account.setRoles(converter(accountDTO));
        return account;
    }

    private static Role converter(AccountDTO accountDTO){
        RoleDTO roleDTO = accountDTO.getRoleDTO();
        if (roleDTO.name().equals(RoleDTO.ROLE_ADMINISTRATOR.name())){
            return Role.ROLE_ADMINISTRATOR;
        }else{
            return Role.ROLE_AUTHOR;
        }
    }

    private static Collection<Post> converterList(Collection<PostDTO> dtoPostList){
        if (dtoPostList == null){
            return null;
        }else{
            return dtoPostList.stream()
                    .map(Post::from)
                    .collect(Collectors.toList());
        }
    }



}
