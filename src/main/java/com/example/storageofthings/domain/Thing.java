package com.example.storageofthings.domain;

import com.example.storageofthings.domain.security.ThingUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String wrnt;
    @ManyToMany(mappedBy = "things")
    private Set<ThingUser> thingUser;
}