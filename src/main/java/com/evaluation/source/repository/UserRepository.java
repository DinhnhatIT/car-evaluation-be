package com.evaluation.source.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.source.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>  {

    Optional<User> findByUserId(UUID userId);
    
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
