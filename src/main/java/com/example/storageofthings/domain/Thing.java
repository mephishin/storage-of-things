package com.example.storageofthings.domain;

import com.example.storageofthings.domain.security.ThingUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.text.MessageFormat;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String wrnt;
    @ManyToMany(mappedBy = "things")
    private Set<ThingUser> thingUser;
    @OneToOne(mappedBy = "thing", cascade = CascadeType.PERSIST)
    private Place place;

    @Override
    public String toString() {
        return MessageFormat.format("[name={0}, thingUser={1}]", name, thingUser.stream().map(ThingUser::getUsername).toList());
    }
}