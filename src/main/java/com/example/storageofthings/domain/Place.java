package com.example.storageofthings.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.text.MessageFormat;

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
    @OneToOne
    @JoinColumn(name = "thing_id", referencedColumnName = "id")
    private Thing thing;

    @Override
    public String toString() {
        return MessageFormat.format("[name={0}, thing={1}]", name, thing.getName());
    }
}
