package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.models.PostModel;
import a.gleb.reactiverest.service.AccountWebClientService;
import a.gleb.reactiverest.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/author_authorization")
public class AuthorAuthorizationController {

    private final PostWebClientService postWebClientService;
    private final AccountWebClientService accountWebClientService;

    @Autowired
    public AuthorAuthorizationController(PostWebClientService postWebClientService, AccountWebClientService accountWebClientService) {
        this.postWebClientService = postWebClientService;
        this.accountWebClientService = accountWebClientService;
    }

    @PostMapping("/create/post")
    public Mono<PostModel> createNewPost(@RequestBody final PostModel postModel){
        return postWebClientService.createPost(postModel);
    }

    @PutMapping("/edit/post/{id}")
    public Mono<PostModel> editSelectedPost(@PathVariable final String id,
                                            @RequestBody final PostModel postModel){
        return postWebClientService.editSelectedPost(id, postModel);
    }

    @DeleteMapping("/delete/post/{id}")
    public Mono<PostModel> deleteSelectedPost(@PathVariable final String id){
        return postWebClientService.deleteSelectedPost(id);
    }

    @PutMapping("/edit/account/{id}")
    public Mono<Account> editSelectedAccount(@PathVariable final String id,
                                             @RequestBody final Account account){
        return accountWebClientService.editSelectedAccount(id, account);
    }

    @DeleteMapping("/delete/account/{id}")
    public Mono<Account> deleteSelectedAccount(@PathVariable final String id){
        return accountWebClientService.deleteSelectedAccount(id);
    }


}
