package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.PlaceJpaRepo;
import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.domain.Thing;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddThingService {
    private final UserJpaRepo userJpaRepo;
    private final ThingJpaRepo thingJpaRepo;
    private final PlaceJpaRepo placeJpaRepo;

    @Transactional
    public void add(Thing thing, String username) {
        thingJpaRepo.save(thing);
        var user = userJpaRepo.findByUsername(username).orElseThrow();
        user.getThings().add(thing);
        var place = placeJpaRepo.findById(thing.getPlace().getId()).orElseThrow();
        place.setThing(thing);
    }
}
