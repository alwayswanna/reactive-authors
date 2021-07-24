package agleb.databaseservice.model.dto;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private Set<RoleDTO> roleDTO;
    private List<PostDTO> postDTOList = new ArrayList<>();

    public static AccountDTO from(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setName(account.getName());
        accountDTO.setSurname(account.getSurname());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setActive(account.isActive());
        accountDTO.setPostDTOList(
                account.getUser_stories().stream()
                        .map(PostDTO::from)
                        .collect(Collectors.toList())
        );
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
}
