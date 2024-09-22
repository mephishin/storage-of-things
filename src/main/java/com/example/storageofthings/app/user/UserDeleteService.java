package com.example.storageofthings.app.user;

import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {
    private final UserJpaRepo userJpaRepo;
}
