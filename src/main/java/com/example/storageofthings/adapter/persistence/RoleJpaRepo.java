package com.example.storageofthings.adapter.persistence;

import com.example.storageofthings.domain.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepo extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
