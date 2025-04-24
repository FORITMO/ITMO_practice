package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Полное имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    private String fullName;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Телефон должен быть в формате +XXXXXXXXXXX")
    private String phone;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 20, message = "Пароль должен содержать от 6 до 20 символов")
    private String password;
}
