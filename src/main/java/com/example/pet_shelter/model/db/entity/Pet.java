package com.example.pet_shelter.model.db.entity;

import com.example.pet_shelter.model.enums.PetStatus;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private String description;

    @Enumerated(EnumType.STRING)
    private PetStatus status; // LOST, FOUND, IN_SHELTER

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    private LocalDateTime createdAt;
}
