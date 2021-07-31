package agleb.databaseservice.model.dto;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean active;
    private RoleDTO roleDTO;
    private Collection<PostDTO> postDTOList = new ArrayList<>();

    public static AccountDTO from(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setName(account.getName());
        accountDTO.setSurname(account.getSurname());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setActive(account.isActive());
        accountDTO.setPostDTOList(dtoConvertPost(account.getUser_stories()));
        accountDTO.setRoleDTO(converter(account));

        return accountDTO;

    }

    private static RoleDTO converter(Account account){
        Role roles = account.getRoles();
        if (roles.name().equals(Role.ADMINISTRATOR.name())){
            return RoleDTO.ADMINISTRATOR;
        }else{
            return RoleDTO.AUTHOR;
        }
    }

    private static Collection<PostDTO> dtoConvertPost(Collection<Post> postList){
        if (postList == null){
            return null;
        }else{
            return postList.stream()
                    .map(PostDTO::from)
                    .collect(Collectors.toList());
        }
    }
}
