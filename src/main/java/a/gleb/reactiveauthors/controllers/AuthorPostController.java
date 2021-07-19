package a.gleb.reactiveauthors.controllers;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.service.AuthorPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class AuthorPostController {

    private final AuthorPostService authorPostService;

    @Autowired
    public AuthorPostController(AuthorPostService authorPostService) {
        this.authorPostService = authorPostService;
    }

    @GetMapping("/posts")
    public Flux<AuthorPosts> loadAllPosts(@RequestParam(defaultValue = "0") Long start,
                                          @RequestParam(defaultValue = "3") Long count){
        return authorPostService.allAuthorPosts();
    }

    @GetMapping("/post/{id}")
    public Mono<AuthorPosts> loadPostById(@PathVariable Long id){
        return authorPostService.loadPostById(id);
    }

    @PostMapping("/post")
    public Mono<AuthorPosts> addNewPost(@RequestBody AuthorPosts authorPosts, @RequestParam String userName){
        return authorPostService.addAuthorPost(authorPosts, userName);
    }

    @PutMapping("/post/{id}")
    public Mono<AuthorPosts> editSelectedAuthorPost(@RequestBody AuthorPosts authorPost){
        return authorPostService.editSelectedPost(authorPost);
    }

    @DeleteMapping("/post/{id}")
    public Mono<AuthorPosts> deleteSelectedPost(@PathVariable Long id){
        return authorPostService.deleteSelectedAuthorPost(id);
    }


}
