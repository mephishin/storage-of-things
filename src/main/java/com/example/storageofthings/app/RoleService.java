package com.example.storageofthings.app;

import com.example.storageofthings.adapter.persistence.RoleJpaRepo;
import com.example.storageofthings.domain.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleJpaRepo roleJpaRepo;

//    public Optional<Role> findByName(String name) {
//        return roleJpaRepo.findByName(name);
//    }
}
