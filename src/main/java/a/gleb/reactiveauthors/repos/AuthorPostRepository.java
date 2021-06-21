package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuthorPostRepository extends ReactiveCrudRepository<AuthorPosts, Long> {
}
