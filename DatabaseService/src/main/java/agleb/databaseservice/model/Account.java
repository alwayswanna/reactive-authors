package agleb.databaseservice.model;

import agleb.databaseservice.model.dto.AccountDTO;
import agleb.databaseservice.model.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jshell.EvalException;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;
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

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
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
        //TODO: create check on null;
        account.setUser_stories(
                accountDTO.getPostDTOList().stream()
                        .map(Post::from)
                        .collect(Collectors.toList())
        );
        account.setRoles(converter(accountDTO));
        return account;
    }

    private static Set<Role> converter(AccountDTO accountDTO){
        Set<Role> roles = new HashSet<>();
        if (accountDTO.getRoleDTO().stream().findFirst().get() == RoleDTO.ADMINISTRATOR){
            roles.add(Role.ADMINISTRATOR);
        }else{
            roles.add(Role.AUTHOR);
        }
        return roles;
    }



}
