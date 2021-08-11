package a.gleb.reactiverest.service;

import a.gleb.reactiverest.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {

    private final AccountWebClientService accountWebClientService;

    public Mono<Account> findByUsername(String username){
        return accountWebClientService.getAccountByUsername(username);
    }
}
