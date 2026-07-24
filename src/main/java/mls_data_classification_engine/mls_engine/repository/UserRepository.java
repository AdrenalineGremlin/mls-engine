package mls_data_classification_engine.mls_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import mls_data_classification_engine.mls_engine.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    // optional used for either the username exists or doesnt
    Optional<User> findByUsername(String username);
}
