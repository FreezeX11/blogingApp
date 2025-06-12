package com.Backend.Backend.Repositories;

import com.Backend.Backend.Entities.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentUserRepository extends JpaRepository<ParentUser, Long> {
    Optional<ParentUser> findByUsername(String username);
    Optional<ParentUser> findByEmail(String email);
}
