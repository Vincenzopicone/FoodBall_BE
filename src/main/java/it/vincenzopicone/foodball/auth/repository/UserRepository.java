package it.vincenzopicone.foodball.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.model.Partita;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    @Query(value="SELECT u FROM User u ORDER BY RANDOM() LIMIT 1")
	User findByUserRandom();
}
