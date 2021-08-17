package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.models.PostModel;
import a.gleb.reactiverest.service.AccountWebClientService;
import a.gleb.reactiverest.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedController {

    private final PostWebClientService postWebClientService;
    private final AccountWebClientService accountWebClientService;

    @Autowired
    public UnauthorizedController(PostWebClientService postWebClientService, AccountWebClientService accountWebClientService) {
        this.postWebClientService = postWebClientService;
        this.accountWebClientService = accountWebClientService;
    }

    @GetMapping("/posts")
    public Flux<PostModel> getAllStories(){
        return postWebClientService.getAllPost()
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    @GetMapping("/post/{id}")
    public Mono<PostModel> getSelectedPost(@PathVariable final String id){
        return postWebClientService.getPostById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    @GetMapping("/account/{id}")
    public Mono<Account> getAccountById(@PathVariable final String id){
        return accountWebClientService.getAccountById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    @PostMapping("/create/account")
    public Mono<Account> createNewAccount(@RequestBody final Account account){
        return accountWebClientService.createNewAccount(account)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public <T> Mono<T> monoResponseStatusNotFoundException(){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "RequestException: request return NULL response"));
    }

}
