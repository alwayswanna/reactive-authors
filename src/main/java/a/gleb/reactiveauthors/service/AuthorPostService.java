package a.gleb.reactiveauthors.service;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.exceptions.NoSuchPostWithSelectedIdException;
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

    public Flux<AuthorPosts> allAuthorPosts() {
        return authorPostRepository.findAll();
    }

    public Mono<AuthorPosts> addAuthorPost(AuthorPosts authorPosts, String user) {
        authorPosts.setAuthorUserName(user);
        authorPosts.setLikes(0L);
        return authorPostRepository.save(authorPosts);
    }

    public Mono<AuthorPosts> editSelectedPost(AuthorPosts authorPost) {
        Mono<AuthorPosts> authorPostsCheck = authorPostRepository.findById(authorPost.getId());
        if (authorPostsCheck == null) {
            throw new NoSuchPostWithSelectedIdException("NoSuchPostWithSelectedIdException: " +
                    "there are no [AuthorPost] for edit with selected [ID] = " + authorPost.getId());
        } else {
            return authorPostRepository.save(authorPost);
        }
    }

    public Mono<AuthorPosts> deleteSelectedAuthorPost(Long id) {
        return authorPostRepository.findById(id)
                .flatMap(existPost ->
                        authorPostRepository.delete(existPost)
                                .then(Mono.just(existPost)));
    }


    public Mono<AuthorPosts> loadPostById(Long id) {
        return authorPostRepository.findById(id);
    }
}
