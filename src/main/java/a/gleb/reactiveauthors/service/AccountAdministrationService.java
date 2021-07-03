package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountAdministrationService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountAdministrationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> createAccount(Account account){
        return accountRepository.save(account);
    }

}
