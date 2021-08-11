package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.models.PostModel;
import a.gleb.reactiverest.service.AccountWebClientService;
import a.gleb.reactiverest.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/author")
public class AuthorAuthorizationController {

    private final PostWebClientService postWebClientService;
    private final AccountWebClientService accountWebClientService;

    @Autowired
    public AuthorAuthorizationController(PostWebClientService postWebClientService, AccountWebClientService accountWebClientService) {
        this.postWebClientService = postWebClientService;
        this.accountWebClientService = accountWebClientService;
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<ResponseEntity<Mono<PostModel>>> createNewPost(@RequestBody final PostModel postModel){
        var response = postWebClientService.createPost(postModel);
        return Mono.just(ResponseEntity.ok(response));
    }

    @PutMapping("/edit/post/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<PostModel> editSelectedPost(@PathVariable final String id,
                                            @RequestBody final PostModel postModel){
        return postWebClientService.editSelectedPost(id, postModel);
    }

    @DeleteMapping("/delete/post/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<PostModel> deleteSelectedPost(@PathVariable final String id){
        return postWebClientService.deleteSelectedPost(id);
    }

    @PutMapping("/edit/account/{id}")
    public Mono<Account> editSelectedAccount(@PathVariable final String id,
                                             @RequestBody final Account account){
        return accountWebClientService.editSelectedAccount(id, account);
    }

    @DeleteMapping("/delete/account/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMINISTRATOR')")
    public Mono<Account> deleteSelectedAccount(@PathVariable final String id){
        return accountWebClientService.deleteSelectedAccount(id);
    }


}
