package agleb.databaseservice.services;

import agleb.databaseservice.model.Account;
import agleb.databaseservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Account editSelectedAccount(Long id, Account account){
        Account toEditAccount = getAccountById(id);
        toEditAccount.setUsername(account.getUsername());
        toEditAccount.setPassword(account.getPassword());
        toEditAccount.setName(account.getName());
        toEditAccount.setSurname(account.getSurname());
        toEditAccount.setEmail(account.getEmail());
        toEditAccount.setActive(account.isActive());
        toEditAccount.setRoles(account.getRoles());
        toEditAccount.setUser_stories(account.getUser_stories());
            return accountRepository.save(toEditAccount);
    }

    public Account removeSelectedAccount(Long id){
        Account account = getAccountById(id);
        accountRepository.delete(account);
        return account;
    }




}
