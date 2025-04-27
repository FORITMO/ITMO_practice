package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserUpdatePasswordRequest {
    @NotBlank(message = "Текущий пароль обязателен")
    private String currentPassword;

    @NotBlank(message = "Новый пароль обязателен")
    @Size(min = 6, max = 20, message = "Пароль должен содержать от 6 до 20 символов")
    private String newPassword;

    @NotBlank(message = "Подтверждение пароля обязательно")
    private String confirmPassword;
}