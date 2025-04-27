package com.example.pet_shelter.model.dto.request;

import com.example.pet_shelter.model.enums.VolunteerStatus;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VolunteerRequest {
    @NotBlank(message = "Полное имя обязательно")
    private String fullName;

    @NotBlank(message = "Телефон обязателен")
    private String phone;

    @NotNull(message = "Статус обязателен")
    private VolunteerStatus status;
}