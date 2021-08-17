package agleb.databaseservice.services;

import agleb.databaseservice.exceptions.NoSuchAccountException;
import agleb.databaseservice.model.Account;
import agleb.databaseservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final AccountRepository accountRepository;

    @Autowired
    public SecurityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account securityAuthorization(String username){
        Account account = accountRepository.getAccountByUsername(username);
        if (account == null){
            throw new NoSuchAccountException("NoSuchAccountException: account with [username]: " + username + " does not exist in database");
        }else{
            return account;
        }
    }



}
