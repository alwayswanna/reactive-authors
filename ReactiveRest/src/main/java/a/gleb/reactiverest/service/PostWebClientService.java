package a.gleb.reactiverest.service;

import a.gleb.reactiverest.models.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PostWebClientService {

    private final WebClient webClient;

    @Autowired
    public PostWebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<PostModel> getAllPost(){
        return webClient
                .get()
                .uri(String.join("", "/posts"))
                .retrieve()
                .bodyToFlux(PostModel.class);
    }

    public Mono<PostModel> createPost(PostModel postModel){
        return webClient.post()
                .uri(String.join("", "/post"))
                .body(Mono.just(postModel), PostModel.class)
                .retrieve()
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> getPostById(String id){
        return webClient
                .get()
                .uri(String.join("", "/post/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new RuntimeException("Message error!")))
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> editSelectedPost(String id, PostModel postModel){
        return webClient
                .put()
                .uri(String.join("", "/post/", id))
                .body(Mono.just(postModel), PostModel.class)
                .retrieve()
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> deleteSelectedPost(String id){
        return webClient
                .delete()
                .uri(String.join("", "/post/", id))
                .retrieve()
                .bodyToMono(PostModel.class);
    }
}


