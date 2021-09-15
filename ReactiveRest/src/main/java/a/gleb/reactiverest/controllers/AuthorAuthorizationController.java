package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.models.PostModel;
import a.gleb.reactiverest.service.AccountWebClientService;
import a.gleb.reactiverest.service.CheckingBodyService;
import a.gleb.reactiverest.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/author")
public class AuthorAuthorizationController {

    private final PostWebClientService postWebClientService;
    private final AccountWebClientService accountWebClientService;
    private final CheckingBodyService checkingBodyService;

    @Autowired
    public AuthorAuthorizationController(PostWebClientService postWebClientService, AccountWebClientService accountWebClientService, CheckingBodyService checkingBodyService) {
        this.postWebClientService = postWebClientService;
        this.accountWebClientService = accountWebClientService;
        this.checkingBodyService = checkingBodyService;
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<PostModel> createNewPost(@RequestBody final PostModel postModel) {
        if (checkingBodyService.checkPostModelFromRequest(postModel)){
            return postWebClientService.createPost(postModel)
                    .switchIfEmpty(monoResponseStatusNotFountException());
        }else {
            return null;
        }
    }

    @PutMapping("/edit/post/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")

    public Mono<PostModel> editSelectedPost(@PathVariable final String id,
                                            @RequestBody final PostModel postModel) {
        if (checkingBodyService.checkPostModelFromRequest(postModel)){
            return postWebClientService.editSelectedPost(id, postModel)
                    .switchIfEmpty(monoResponseStatusNotFountException());
        }else {
            return null;
        }
    }

    @DeleteMapping("/delete/post/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<PostModel> deleteSelectedPost(@PathVariable final String id) {
        return postWebClientService.deleteSelectedPost(id)
                .switchIfEmpty(monoResponseStatusNotFountException());
    }

    @PutMapping("/edit/account/{id}")
    public Mono<Account> editSelectedAccount(@PathVariable final String id,
                                             @RequestBody final Account account) {
        if (checkingBodyService.checkAccountModelFromRequest(account)){
            return accountWebClientService.editSelectedAccount(id, account)
                    .switchIfEmpty(monoResponseStatusNotFountException());
        }else {
            return null;
        }
    }

    @DeleteMapping("/delete/account/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<Account> deleteSelectedAccount(@PathVariable final String id) {
        return accountWebClientService.deleteSelectedAccount(id)
                .switchIfEmpty(monoResponseStatusNotFountException());
    }

    public <T> Mono<T> monoResponseStatusNotFountException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "RequestException: request return NULL response"));
    }


}
