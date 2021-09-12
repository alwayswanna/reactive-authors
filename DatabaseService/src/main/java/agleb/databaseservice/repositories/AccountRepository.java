package agleb.databaseservice.repositories;

import agleb.databaseservice.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> getAccountByUsername(String username);
    Account findAccountById(Long id);

}
