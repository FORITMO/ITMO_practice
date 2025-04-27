package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.PetStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PetResponse {
    private Long id;
    private String name;
    private String breed;
    private String description;
    private PetStatus status;
    private Long shelterId;
    private LocalDateTime createdAt;
}