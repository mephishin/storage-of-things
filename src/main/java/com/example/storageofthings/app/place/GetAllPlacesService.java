package com.example.storageofthings.app.place;

import com.example.storageofthings.adapter.persistence.PlaceJpaRepo;
import com.example.storageofthings.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllPlacesService {
    private final PlaceJpaRepo placeJpaRepo;

    public List<Place> get() {
        return (List<Place>) placeJpaRepo.findAll();
    }
}
