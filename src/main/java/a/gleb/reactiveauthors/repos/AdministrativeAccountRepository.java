package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AdministrativeAccountRepository extends ReactiveCrudRepository<Account, Long> {
}
