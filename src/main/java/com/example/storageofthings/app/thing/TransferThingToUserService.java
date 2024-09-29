package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.app.user.GetUserByUsernameService;
import com.example.storageofthings.domain.security.ThingUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class TransferThingToUserService {
    private final ThingJpaRepo thingJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final GetUserByUsernameService getUserByUsernameService;

    @Transactional
    public void transfer(Long receiverId, Long thingId, String senderName) {
        var sender = (ThingUser) getUserByUsernameService.loadUserByUsername(senderName);
        var receiver = userJpaRepo.findById(receiverId).orElseThrow();
        var thing = thingJpaRepo.findById(thingId).orElseThrow();

        sender.getThings().remove(thing);

        receiver.getThings().add(thing);
    }
}

