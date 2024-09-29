package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThingDeleteService {
    private final ThingJpaRepo thingJpaRepo;
    private final UserJpaRepo userJpaRepo;

    @Transactional
    public void deleteThing(Long thingId, String username) {
        if (thingJpaRepo.findById(thingId).isPresent()) {
            var thing = thingJpaRepo.findById(thingId).orElseThrow();
            var user = userJpaRepo.findByUsername(username).orElseThrow();
            var place = thing.getPlace();
            place.setThing(null);
            thing.getThingUser().remove(user);
            user.getThings().remove(thing);
            thingJpaRepo.deleteById(thingId);
        } else {
            throw new IllegalArgumentException("No thing with id: " + thingId);
        }
    }
}
