package agleb.databaseservice.model.dto;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.model.Role;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
//TODO: review models for database;
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean active;
    private Set<RoleDTO> roleDTO;
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

    private static Set<RoleDTO> converter(Account account){
        Set<RoleDTO> roleDTOS = new HashSet<>();
        if (account.getRoles().stream().findFirst().get() == Role.ADMINISTRATOR){
            roleDTOS.add(RoleDTO.ADMINISTRATOR);
        }else{
            roleDTOS.add(RoleDTO.AUTHOR);
        }
        return roleDTOS;
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
