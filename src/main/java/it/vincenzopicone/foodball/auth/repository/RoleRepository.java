package it.vincenzopicone.foodball.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vincenzopicone.foodball.auth.entity.ERole;
import it.vincenzopicone.foodball.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
