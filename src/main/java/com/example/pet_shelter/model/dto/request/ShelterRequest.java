package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ShelterRequest {
    @NotBlank(message = "Название приюта обязательно")
    private String name;

    @NotBlank(message = "Адрес обязателен")
    private String address;

    @NotBlank(message = "Контактный телефон обязателен")
    private String phone;
}