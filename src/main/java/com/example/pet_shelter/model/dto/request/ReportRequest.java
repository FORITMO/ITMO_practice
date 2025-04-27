package com.example.pet_shelter.model.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReportRequest {
    @NotBlank(message = "Текст отчета обязателен")
    private String text;

    @NotNull(message = "ID заявки обязательно")
    private Long applicationId;
}
