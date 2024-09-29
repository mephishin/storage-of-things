package com.example.storageofthings.app.user;

import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.security.ThingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersExceptAuthenticated {
    private final UserJpaRepo userJpaRepo;

    public List<ThingUser> get(String username) {
        return userJpaRepo
                .findAll()
                .stream()
                .filter(user -> !user.getUsername().equals(username))
                .toList();
    }
}
