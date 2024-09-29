package com.example.storageofthings.adapter.persistence;

import com.example.storageofthings.domain.Thing;
import com.example.storageofthings.domain.security.ThingUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingJpaRepo extends CrudRepository<Thing, Long> {
}
