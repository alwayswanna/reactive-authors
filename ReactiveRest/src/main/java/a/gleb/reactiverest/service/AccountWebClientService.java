package a.gleb.reactiverest.service;

import a.gleb.reactiverest.exceptions.DatabaseResponseException;
import a.gleb.reactiverest.exceptions.DatabaseServerException;
import a.gleb.reactiverest.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t load all posts. HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t load all posts. HttpStatusCode: [500]")))
                .bodyToFlux(Account.class);
    }

    public Mono<Account> getAccountById(String id){
        return webClient
                .get()
                .uri(String.join("", "/account/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: there are no account with [id]: " + id + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: there are no account with [id]: " + id + ". HttpStatusCode: [500]")))
                .bodyToMono(Account.class);
    }

    public Mono<Account> createNewAccount(Account account){
        return webClient
                .post()
                .uri(String.join("", "/account"))
                .body(Mono.just(account), Account.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t create new account: " + account + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t create new account: " + account + ". HttpStatusCode: [500]")))
                .bodyToMono(Account.class);
    }


    public Mono<Account> editSelectedAccount(String id, Account account){
        return webClient
                .put()
                .uri(String.join("", "/account/", id))
                .body(Mono.just(account), Account.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t edit account with [id]: " + id + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t edit account with [id]: " + id + ". HttpStatusCode: [500]")))
                .bodyToMono(Account.class);
    }

    public Mono<Account> deleteSelectedAccount(String id){
        return webClient
                .delete()
                .uri(String.join("", "/account/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t delete account with [id]: " + id + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t delete account with [id]: " + id + ". HttpStatusCode: [500]")))
                .bodyToMono(Account.class);
    }

    public Mono<Account> getAccountByUsername(String username) {
        return webClient
                .get()
                .uri(String.join("", "/account/usrname/", username))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: there are no account with [username]: " + username + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: there are no account with [username]: " + username + ". HttpStatusCode: [500]")))
                .bodyToMono(Account.class);
    }
}
