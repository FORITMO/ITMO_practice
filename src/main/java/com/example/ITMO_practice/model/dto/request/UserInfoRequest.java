package com.example.ITMO_practice.model.dto.request;

import com.example.ITMO_practice.model.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoRequest {
    @NotEmpty
    @Schema(description = "Email пользователя")
    private String email;
    @Schema(description = "Пароль пользователя")
    private String password;
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Фамилия пользователя")
    private String middleName;
    @Schema(description = "Отчество пользователя")
    private String lastName;
    @Schema(description = "Возраст пользователя")
    @NotNull
    private Integer age;
    @Schema(description = "Пол пользователя")
    private Gender gender;
}
