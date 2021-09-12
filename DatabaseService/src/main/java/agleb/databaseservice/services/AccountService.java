package agleb.databaseservice.services;

import agleb.databaseservice.exceptions.DuplicateUsernameException;
import agleb.databaseservice.exceptions.NoSuchAccountException;
import agleb.databaseservice.model.Account;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final PostService postService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PostService postService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.postService = postService;
    }

    public Account createAccount(Account account) {
        if (!findAccountByUsername(account.getUsername())) {
            account.setPassword(
                    passwordEncoder.encode(account.getPassword()));
            return accountRepository.save(account);
        } else {
            throw new DuplicateUsernameException("DuplicateUsernameException: user with [username]:  " + account.getUsername() +
                    "  already exist in database");
        }


    }

    public List<Account> getAllAccounts() {
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Account getAccountById(Long id) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) {
            throw new NoSuchAccountException("NoSuchAccountException: account with [id]: " + id + " does not exist in database");
        } else {
            return account;
        }
    }

    @Transactional
    public Account editSelectedAccount(Long id, Account account) {
        Account toEditAccount = getAccountById(id);
        if (toEditAccount == null) {
            throw new NoSuchAccountException("NoSuchAccountException: account with [id]: " + id + " does not exist in database");
        } else {
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
    }

    public void removeSelectedAccount(Account account) {
        Account delAccount = getAccountById(account.getId());
        if (delAccount == null) {
            throw new NoSuchAccountException("NoSuchAccountException: account with [id]: " + account.getId() + " does not exist in database");
        } else {

            Collection<Post> posts = account.getUser_stories();
            if (!posts.isEmpty()) {
                posts.forEach(var -> postService.removePostById(var.getId()));
            }
            accountRepository.delete(account);
        }
    }

    public boolean findAccountByUsername(String username) {
        Optional<Account> account = accountRepository.getAccountByUsername(username);
        return account.isPresent();
    }

}


