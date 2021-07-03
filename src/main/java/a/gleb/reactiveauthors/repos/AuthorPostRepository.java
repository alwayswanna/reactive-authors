package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorPostRepository extends ReactiveCrudRepository<AuthorPosts, Long> {
}
