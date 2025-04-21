package com.example.pet_shelter.model.dto.request;

import com.example.pet_shelter.model.enums.PetStatus;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PetRequest {
    @NotBlank(message = "Имя обязательно")
    private String name;

    @NotBlank(message = "Порода обязательна")
    private String breed;

    @NotNull(message = "Статус обязателен")
    private PetStatus status;
}