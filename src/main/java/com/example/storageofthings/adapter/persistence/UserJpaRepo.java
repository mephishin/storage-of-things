package com.example.storageofthings.adapter.persistence;

import com.example.storageofthings.domain.security.ThingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<ThingUser, Long> {
    Optional<ThingUser> findByUsername(String username);
}
