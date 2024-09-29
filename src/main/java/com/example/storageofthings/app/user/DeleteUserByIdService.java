package com.example.storageofthings.app.user;

import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserByIdService {
    private final UserJpaRepo userJpaRepo;
}
