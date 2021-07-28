package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.service.AccountWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive_service")
public class AccountController {

    private final AccountWebClientService accountWebClientService;

    @Autowired
    public AccountController(AccountWebClientService accountWebClientService) {
        this.accountWebClientService = accountWebClientService;
    }

    @GetMapping("/accounts")
    public Flux<Account> getAllAccounts(){
        return accountWebClientService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    public Mono<Account> getAccountById(@PathVariable final String id){
        return accountWebClientService.getAccountById(id);
    }

    @PostMapping("/account")
    public Mono<Account> createNewAccount(@RequestBody final Account account){
        return accountWebClientService.createNewAccount(account);
    }

    @PutMapping("/account/{id}")
    public Mono<Account> editSelectedAccount(@PathVariable final String id,
                                             @RequestBody final Account account){
        return accountWebClientService.editSelectedAccount(id, account);
    }

    @DeleteMapping("/account/{id}")
    public Mono<Account> deleteSelectedAccount(@PathVariable final String id){
        return accountWebClientService.deleteSelectedAccount(id);
    }


}
