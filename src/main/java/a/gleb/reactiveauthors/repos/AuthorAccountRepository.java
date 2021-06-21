package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.AuthorAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuthorAccountRepository extends ReactiveCrudRepository<AuthorAccount, Long> {
}
