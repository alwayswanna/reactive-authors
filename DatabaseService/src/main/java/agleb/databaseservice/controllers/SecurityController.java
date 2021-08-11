package agleb.databaseservice.controllers;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.dto.AccountDTO;
import agleb.databaseservice.services.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db_service/account/usrname/")
public class SecurityController {

    private final SecurityService securityService;


    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDTO> authorizeAccount(@PathVariable String username){
        Account account = securityService.securityAuthorization(username);
        return new ResponseEntity<>(AccountDTO.from(account), HttpStatus.OK);
    }

}
