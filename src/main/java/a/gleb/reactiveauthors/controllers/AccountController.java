package a.gleb.reactiveauthors.controllers;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.service.AccountAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

    private final AccountAdministrationService accountAdministrationService;

    @Autowired
    public AccountController(AccountAdministrationService accountAdministrationService) {
        this.accountAdministrationService = accountAdministrationService;
    }

    @PostMapping("/user")
    public Mono<Account> createAccount(@RequestBody Account account){
        return accountAdministrationService.createAccount(account);
    }

    @GetMapping("/users")
    public Flux<Account> showAllAccounts(){
        return  accountAdministrationService.showAllAccounts();
    }
}
