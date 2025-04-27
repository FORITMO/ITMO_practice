package com.example.pet_shelter.model.dto.request;


import com.example.pet_shelter.model.enums.ApplicationType;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ApplicationRequest {
    @NotNull(message = "Тип заявки обязателен")
    private ApplicationType type;

    private String content;

    @NotNull(message = "ID пользователя обязательно")
    private Long userId;

    @NotNull(message = "ID питомца обязательно")
    private Long petId;
}
