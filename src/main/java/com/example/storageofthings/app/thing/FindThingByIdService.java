package com.example.storageofthings.app.thing;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.domain.Thing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindThingByIdService {
    private final ThingJpaRepo thingJpaRepo;

    public Thing find(Long id) {
        return thingJpaRepo.findById(id).orElseThrow();
    }
}
