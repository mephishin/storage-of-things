package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.Thing;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindThingsByThingUserService {
    private final ThingJpaRepo thingJpaRepo;
    private final UserJpaRepo userJpaRepo;

    @Transactional
    public List<Thing> find(String username) {
        var thingUser = userJpaRepo.findByUsername(username).orElseThrow();
        log.info("Get things by username: " + username);
        log.info("Things: " + thingJpaRepo.findAll());
        return StreamSupport
                .stream(thingJpaRepo.findAll().spliterator(), false)
                .filter(_thing -> _thing.getThingUser().stream().findAny().orElseThrow().equals(thingUser))
                .toList();
    }
}
