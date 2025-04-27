package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TaskRequest {
    @NotBlank(message = "Описание задачи обязательно")
    private String description;

    @NotNull(message = "ID волонтера обязательно")
    private Long volunteerId;
}