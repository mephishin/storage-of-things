package com.example.storageofthings;

import com.example.storageofthings.adapter.persistence.RoleJpaRepo;
import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.Thing;
import com.example.storageofthings.domain.security.Role;
import com.example.storageofthings.domain.security.ThingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class StorageOfThingsApplication {
    private final ThingJpaRepo thingJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final RoleJpaRepo roleJpaRepo;

    public static void main(String[] args) {
        SpringApplication.run(StorageOfThingsApplication.class, args);
    }

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
}
