package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.dto.Role;
import a.gleb.reactiveauthors.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Service
public class AccountAdministrationService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountAdministrationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> createAccount(Account account){
        account.setActive(true);
        account.setRoles(Role.ADMINISTRATOR);
        return accountRepository.save(account);
    }

    public Flux<Account> showAllAccounts(){
        return accountRepository.findAll();
    }

}
