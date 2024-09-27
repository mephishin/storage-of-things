package com.example.storageofthings.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean repair;
    private Boolean work;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thing_id", referencedColumnName = "id")
    private Thing thing;

}
