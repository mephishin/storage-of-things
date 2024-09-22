package com.example.storageofthings.adapter.persistence;

import com.example.storageofthings.domain.Thing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingJpaRepo extends CrudRepository<Thing, Long> {
}
