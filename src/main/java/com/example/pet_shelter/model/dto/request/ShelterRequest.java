package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ShelterRequest {
    @NotBlank(message = "Название приюта обязательно")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов")
    private String name;

    @NotBlank(message = "Адрес обязателен")
    @Size(max = 200, message = "Адрес не должен превышать 200 символов")
    private String address;

    @NotBlank(message = "Контактный телефон обязателен")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Телефон должен быть в формате +XXXXXXXXXXX")
    private String phone;
}