package com.example.pet_shelter.model.dto.request;

import com.example.pet_shelter.model.enums.PetStatus;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class PetRequest {
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String name;

    @NotBlank(message = "Порода обязательна")
    @Size(max = 100, message = "Порода не должна превышать 100 символов")
    private String breed;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;

    @NotNull(message = "Статус обязателен")
    private PetStatus status;

    @NotNull(message = "ID приюта обязательно")
    @Positive(message = "ID приюта должен быть положительным числом")
    private Long shelterId;
}