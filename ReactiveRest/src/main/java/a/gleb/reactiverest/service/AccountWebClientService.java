package a.gleb.reactiverest.service;

import a.gleb.reactiverest.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountWebClientService {

    private final WebClient webClient;

    @Autowired
    public AccountWebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Account> getAllAccounts(){
        return webClient
                .get()
                .uri(String.join("", "/accounts"))
                .retrieve()
                .bodyToFlux(Account.class);
    }

    public Mono<Account> getAccountById(String id){
        return webClient
                .get()
                .uri(String.join("", "/account/", id))
                .retrieve()
                .bodyToMono(Account.class);
    }

    public Mono<Account> createNewAccount(Account account){
        return webClient
                .post()
                .uri(String.join("", "/account"))
                .body(Mono.just(account), Account.class)
                .retrieve()
                .bodyToMono(Account.class);
    }


    public Mono<Account> editSelectedAccount(String id, Account account){
        return webClient
                .put()
                .uri(String.join("", "/account/", id))
                .body(Mono.just(account), Account.class)
                .retrieve()
                .bodyToMono(Account.class);
    }

    public Mono<Account> deleteSelectedAccount(String id){
        return webClient
                .delete()
                .uri(String.join("", "/account/", id))
                .retrieve()
                .bodyToMono(Account.class);
    }

    public Mono<Account> getAccountByUsername(String username) {
        return webClient
                .get()
                .uri(String.join("", "/account/", username))
                .retrieve()
                .bodyToMono(Account.class);
    }
}
