package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.exceptions.NoSuchPostWithSelectedIdException;
import a.gleb.reactiveauthors.repos.AuthorPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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

    public Mono<AuthorPosts> editSelectedPost(AuthorPosts authorPost){
        Mono<AuthorPosts> authorPostsCheck = authorPostRepository.findById(authorPost.getId());
        if (authorPostsCheck == null){
            throw new NoSuchPostWithSelectedIdException("NoSuchPostWithSelectedIdException: " +
                    "there are no [AuthorPost] for edit with selected [ID] = " + authorPost.getId());
        }else{
            return authorPostRepository.save(authorPost);
        }
    }

    public Flux<AuthorPosts> deleteSelectedAuthorPost(AuthorPosts authorPosts){
        Mono<AuthorPosts> authorPostCheck = authorPostRepository.findById(authorPosts.getId());
        if (authorPostCheck == null){
            throw new NoSuchPostWithSelectedIdException("NoSuchPostWithSelectedIdException: " +
                    "there are no [AuthorPost] for delete with selected [ID] = " + authorPosts.getId());
        }else{
            authorPostRepository.delete(authorPosts);
        }
        return authorPostRepository.findAll();
    }





}
