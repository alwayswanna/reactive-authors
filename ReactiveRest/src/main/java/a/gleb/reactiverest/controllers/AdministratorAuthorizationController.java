package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.service.AccountWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/administrator_authorization")
public class AdministratorAuthorizationController {

    private final AccountWebClientService accountWebClientService;

    @Autowired
    public AdministratorAuthorizationController(AccountWebClientService accountWebClientService) {
        this.accountWebClientService = accountWebClientService;
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Flux<Account> getAllAccounts(){
        return accountWebClientService.getAllAccounts();
    }
}
