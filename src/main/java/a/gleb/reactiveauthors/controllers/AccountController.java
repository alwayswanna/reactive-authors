package a.gleb.reactiveauthors.controllers;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.service.AccountAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class AccountController {

    private final AccountAdministrationService accountAdministrationService;

    @Autowired
    public AccountController(AccountAdministrationService accountAdministrationService) {
        this.accountAdministrationService = accountAdministrationService;
    }

    @PostMapping("/user")
    public Mono<Account> createAccount(@RequestBody Account account) {
        return accountAdministrationService.createAccount(account);
    }

    @GetMapping("/users")
    public Flux<Account> showAllAccounts() {
        return accountAdministrationService.showAllAccounts();
    }

    @GetMapping("/user/{id}")
    public Mono<Account> userAccountById(@PathVariable Long id) {
        return accountAdministrationService.accountById(id);
    }

    @PutMapping("/user")
    public Mono<Account> editSelectedAccount(@RequestBody Account account) {
        return accountAdministrationService.editSelectedAccount(account);
    }

    @DeleteMapping("/user/{id}")
    public Mono<Account> deleteSelectedUserAccount(@PathVariable Long id) {
        return accountAdministrationService.deleteSelected(id);
    }

    @PostMapping("/login")
    public Mono<Account> loginToAccount(@RequestParam String username, @RequestParam String password){
        System.out.println(username + " " + password);
        //TODO: create login?
        return accountAdministrationService.login(username, password);
    }

}
