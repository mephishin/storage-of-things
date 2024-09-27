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
    public static void main(String[] args) {
        SpringApplication.run(StorageOfThingsApplication.class, args);
    }
}
