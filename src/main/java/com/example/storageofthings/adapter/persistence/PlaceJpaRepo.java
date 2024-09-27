package com.example.storageofthings.adapter.persistence;

import com.example.storageofthings.domain.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceJpaRepo extends CrudRepository<Place, Long> {
}
