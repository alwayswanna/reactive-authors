package agleb.databaseservice.controllers;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.model.dto.AccountDTO;
import agleb.databaseservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/db_service")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> loadAccountById(@PathVariable final Long id){
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(AccountDTO.from(account), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> loadAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountDTO> accountDTOList = accounts
                        .stream()
                        .map(AccountDTO::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createUserAccount(@RequestBody final AccountDTO accountDTO){
        Account account = accountService.createAccount(Account.from(accountDTO));
        return new ResponseEntity<>(AccountDTO.from(account), HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<AccountDTO> removeSelectedAccount(@PathVariable final Long id){
        Account account = accountService.removeSelectedAccount(id);
        return new ResponseEntity<>(AccountDTO.from(account), HttpStatus.OK);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountDTO> editSelectedAccount(
                                @PathVariable final Long id,
                                @RequestBody AccountDTO accountDTO){
        Account account = accountService.editSelectedAccount(id, Account.from(accountDTO));
        return new ResponseEntity<>(AccountDTO.from(account), HttpStatus.OK);
    }
}
