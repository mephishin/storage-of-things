package com.example.storageofthings.app.user;

import com.example.storageofthings.adapter.persistence.RoleJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.security.ThingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSavingService {
    private final UserJpaRepo userJpaRepo;
    private final RoleJpaRepo roleJpaRepo;
    private final UserLoadingService userLoadingService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(ThingUser user) {
        try {
            userLoadingService.loadUserByUsername(user.getUsername());

            throw new IllegalArgumentException("User with name: " + user.getUsername() + " already existed");
        } catch (UsernameNotFoundException e) {
            user.setRoles(Set.of(roleJpaRepo.findByName("ROLE_USER").orElseThrow()));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            userJpaRepo.save(user);
        }
    }
}
