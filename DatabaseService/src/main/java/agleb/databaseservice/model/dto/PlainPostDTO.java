package agleb.databaseservice.model.dto;

import agleb.databaseservice.model.Account;
import lombok.Data;

@Data
public class PlainPostDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;

    public static PlainPostDTO from(Account account){
        PlainPostDTO plainPostDTO = new PlainPostDTO();
        plainPostDTO.setId(account.getId());
        plainPostDTO.setUsername(account.getUsername());
        plainPostDTO.setName(account.getName());
        plainPostDTO.setSurname(account.getSurname());
        plainPostDTO.setEmail(account.getEmail());
        return plainPostDTO;
    }

}
