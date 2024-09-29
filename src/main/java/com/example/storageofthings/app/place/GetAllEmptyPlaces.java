package com.example.storageofthings.app.place;

import com.example.storageofthings.adapter.persistence.PlaceJpaRepo;
import com.example.storageofthings.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GetAllEmptyPlaces {
    private final PlaceJpaRepo placeJpaRepo;

    public List<Place> get() {
        return StreamSupport
                .stream(placeJpaRepo.findAll().spliterator(), false)
                .filter(place -> place.getThing() == null)
                .toList();
    }
}
