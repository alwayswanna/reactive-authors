package a.gleb.reactiverest.service;

import a.gleb.reactiverest.exceptions.DatabaseResponseException;
import a.gleb.reactiverest.exceptions.DatabaseServerException;
import a.gleb.reactiverest.models.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t load all posts. HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t load all posts. HttpStatusCode: [500]")))
                .bodyToFlux(PostModel.class);
    }

    public Mono<PostModel> createPost(PostModel postModel){
        return webClient.post()
                .uri(String.join("", "/post"))
                .body(Mono.just(postModel), PostModel.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t create post: " + postModel.toString() + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t create post: " + postModel.toString() + ".  HttpStatusCode: [500]")))
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> getPostById(String id){
        return webClient
                .get()
                .uri(String.join("", "/post/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: there are no post with [id]: " + id + ". HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                            error -> Mono.error(new DatabaseServerException("DatabaseServerException: there are no post with [id]: " + id + ". HttpStatusCode [500]")))
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> editSelectedPost(String id, PostModel postModel){
        return webClient
                .put()
                .uri(String.join("", "/post/", id))
                .body(Mono.just(postModel), PostModel.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t edit post with [id]: " + id + ".  HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                            error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t edit post with [id]: " + id +  ". Server returned HttpStatusCode [500]")))
                .bodyToMono(PostModel.class);
    }

    public Mono<PostModel> deleteSelectedPost(String id){
        return webClient
                .delete()
                .uri(String.join("", "/post/", id))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new DatabaseResponseException("DatabaseResponseException: can`t delete post with [id]: " + id + ".  HttpStatusCode: [4xx]")))
                .onStatus(HttpStatus::is5xxServerError,
                            error -> Mono.error(new DatabaseServerException("DatabaseServerException: can`t delete post with [id]: " + id + ". HttpStatusCode: [500]")))
                .bodyToMono(PostModel.class);
    }
}


