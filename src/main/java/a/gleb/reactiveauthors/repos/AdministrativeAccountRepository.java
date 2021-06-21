package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.AdministrativeAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AdministrativeAccountRepository extends ReactiveCrudRepository<AdministrativeAccount, Long> {
}
