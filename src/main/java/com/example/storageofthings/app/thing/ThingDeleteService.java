package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThingDeleteService {
    private final ThingJpaRepo thingJpaRepo;

    @Transactional
    public void deleteThing(Long thingId) {
        if (thingJpaRepo.findById(thingId).isPresent()) {
            var thing = thingJpaRepo.findById(thingId).orElseThrow();
            thing.getThingUser().forEach(thingUser -> thingUser.setThings(Set.of()));
            thing.setThingUser(Set.of());
            thingJpaRepo.deleteById(thingId);
        } else {
            throw new IllegalArgumentException("No thing with id: " + thingId);
        }
    }
}
