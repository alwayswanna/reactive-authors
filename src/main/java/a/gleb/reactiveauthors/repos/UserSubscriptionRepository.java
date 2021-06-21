package a.gleb.reactiveauthors.repos;

import a.gleb.reactiveauthors.dto.UserSubscription;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserSubscriptionRepository extends ReactiveCrudRepository<UserSubscription, Long> {
}
