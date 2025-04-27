package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.PetStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PetResponse {
    private Long id;
    private String name;
    private String breed;
    private String description;
    private PetStatus status;
    private Long shelterId;
    private LocalDateTime createdAt;
    private String shelterName; // Добавляем для удобства клиента
}