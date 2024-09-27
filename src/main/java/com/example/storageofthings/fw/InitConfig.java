package com.example.storageofthings.fw;

import com.example.storageofthings.adapter.persistence.PlaceJpaRepo;
import com.example.storageofthings.adapter.persistence.RoleJpaRepo;
import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.Place;
import com.example.storageofthings.domain.Thing;
import com.example.storageofthings.domain.security.Role;
import com.example.storageofthings.domain.security.ThingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitConfig {
    private final PlaceJpaRepo placeJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final RoleJpaRepo roleJpaRepo;

    @Bean
    InitializingBean initRoles() {
        return () -> {
            roleJpaRepo.save(
                    new Role()
                            .setName("ROLE_USER")
            );
            roleJpaRepo.save(
                    new Role()
                            .setName("ROLE_ADMIN")
            );
        };
    }

    @Bean
    InitializingBean initUsers() {
        return () -> {
            userJpaRepo.save(
                    new ThingUser()
                            .setUsername("user")
                            .setPassword(new BCryptPasswordEncoder().encode("user"))
                            .setRoles(Set.of(roleJpaRepo.findByName("ROLE_USER").orElseThrow()))
                            .setThings(Set.of(new Thing()
                                    .setName("Umbrella")
                                    .setDescription("To defend of rain")
                                    .setWrnt("1.5 year")))
            );
            userJpaRepo.save(
                    new ThingUser()
                            .setUsername("admin")
                            .setPassword(new BCryptPasswordEncoder().encode("admin"))
                            .setRoles(Set.of(roleJpaRepo.findByName("ROLE_ADMIN").orElseThrow()))
                            .setThings(Set.of(new Thing()
                                    .setName("Ball")
                                    .setDescription("To play football")
                                    .setWrnt("1 year")))
            );
        };
    }

    @Bean
    InitializingBean initPlace() {
        return () -> {
            placeJpaRepo.save(
                    new Place()
                            .setWork(false)
                            .setRepair(false)
                            .setName("box")
                            .setDescription("big box with something in")
            );
        };
    }
}
