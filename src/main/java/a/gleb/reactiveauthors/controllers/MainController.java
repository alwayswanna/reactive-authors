package a.gleb.reactiveauthors.controllers;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.service.AuthorPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainController {

    private final AuthorPostService authorPostService;

    @Autowired
    public MainController(AuthorPostService authorPostService) {
        this.authorPostService = authorPostService;
    }

    @GetMapping("/posts")
    public Flux<AuthorPosts> loadAllPosts(@RequestParam(defaultValue = "0") Long start,
                                          @RequestParam(defaultValue = "3") Long count){
        return authorPostService.allAuthorPosts();
    }

    @PostMapping("/post")
    public Mono<AuthorPosts> addNewPost(@RequestBody AuthorPosts authorPosts){
        return authorPostService.addAuthorPost(authorPosts);
    }

    /*@GetMapping
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start, @RequestParam(defaultValue = "3") Long count){
        return
                Flux
                        .just(
                                "Hello, reactive!",
                                "More then one",
                                "Third post",
                                "Fourth post",
                                "Fifth post"
                        )
                        .skip(start)
                        .take(count)
                        .map(Message::new);
    }*/

}
