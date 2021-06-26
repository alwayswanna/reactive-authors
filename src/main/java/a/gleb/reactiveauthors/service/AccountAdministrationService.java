package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.Account;
import a.gleb.reactiveauthors.repos.AdministrativeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountAdministrationService {

    private final AdministrativeAccountRepository administrativeAccountRepository;

    @Autowired
    public AccountAdministrationService(AdministrativeAccountRepository administrativeAccountRepository) {
        this.administrativeAccountRepository = administrativeAccountRepository;
    }

    public Mono<Account> createAccount(Account account){
        return administrativeAccountRepository.save(account);
    }

}
