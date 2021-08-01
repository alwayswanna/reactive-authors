package agleb.databaseservice.services;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityService {

    private final AccountRepository accountRepository;

    @Autowired
    public SecurityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account securityAuthorization(String username){
        Account account;
        try{
            account = accountRepository.getAccountByUsername(username);
            return account;
        }catch (Exception e){
            log.warn(e.getMessage());
            //TODO: exception;
            throw new RuntimeException(e.getMessage());
        }
    }



}
