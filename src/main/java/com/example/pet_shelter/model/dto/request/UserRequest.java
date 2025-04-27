package com.example.pet_shelter.model.dto.request;


import com.example.pet_shelter.model.enums.UserRole;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserRequest {
    @NotBlank(message = "Полное имя обязательно")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String fullName;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Телефон должен быть в формате +XXXXXXXXXXX")
    private String phone;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    @Size(max = 100, message = "Email не должен превышать 100 символов")
    private String email;

    @NotNull(message = "Роль пользователя обязательна")
    private UserRole role;
}
