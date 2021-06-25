package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.repos.AuthorPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorPostService {

    private final AuthorPostRepository authorPostRepository;

    @Autowired
    public AuthorPostService(AuthorPostRepository authorPostRepository) {
        this.authorPostRepository = authorPostRepository;
    }

    public Flux<AuthorPosts> allAuthorPosts(){
        return authorPostRepository.findAll();
    }

    public Mono<AuthorPosts> addAuthorPost(AuthorPosts authorPosts){
        return authorPostRepository.save(authorPosts);
    }



}
