package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.dto.Role;
import a.gleb.reactiveauthors.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountAdministrationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountAdministrationService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<Account> createAccount(Account account) {
        account.setActive(true);
        account.setRoles(Role.AUTHOR);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Flux<Account> showAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Account> accountById(Long id) {
        return accountRepository.findById(id);
    }

    public Mono<Account> editSelectedAccount(Account account) {
        account.setActive(true);
        account.setRoles(Role.AUTHOR);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Mono<Account> deleteSelected(Long id) {
        return accountRepository.findById(id)
                .flatMap(dbAccount -> accountRepository.delete(dbAccount)
                        .then(Mono.just(dbAccount)));
    }
}
