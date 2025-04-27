package com.example.pet_shelter.model.dto.request;

import com.example.pet_shelter.model.enums.VolunteerStatus;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class VolunteerRequest {
    @NotNull(message = "ID пользователя обязательно")
    @Positive(message = "ID пользователя должен быть положительным числом")
    private Long userId;

    @NotNull(message = "ID приюта обязательно")
    @Positive(message = "ID приюта должен быть положительным числом")
    private Long shelterId;

    private VolunteerStatus status;
}