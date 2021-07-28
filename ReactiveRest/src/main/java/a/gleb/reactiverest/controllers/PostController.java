package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.models.PostModel;
import a.gleb.reactiverest.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive_service")
public class PostController {

    private final PostWebClientService postWebClientService;

    @Autowired
    public PostController(PostWebClientService postWebClientService) {
        this.postWebClientService = postWebClientService;
    }

    @GetMapping("/posts")
    public Flux<PostModel> getAllStories(){
        return postWebClientService.getAllPost();
    }

    @PostMapping("/post")
    public Mono<PostModel> createNewPost(@RequestBody final PostModel postModel){
        return postWebClientService.createPost(postModel);
    }

    @GetMapping("/post/{id}")
    public Mono<PostModel> getSelectedPost(@PathVariable final String id){
        return postWebClientService.getPostById(id);
    }

    @PutMapping("/post/{id}")
    public Mono<PostModel> editSelectedPost(@PathVariable final String id,
                                            @RequestBody final PostModel postModel){
        return postWebClientService.editSelectedPost(id, postModel);
    }

    @DeleteMapping("/post/{id}")
    public Mono<PostModel> deleteSelectedPost(@PathVariable final String id){
        return postWebClientService.deleteSelectedPost(id);
    }
}
