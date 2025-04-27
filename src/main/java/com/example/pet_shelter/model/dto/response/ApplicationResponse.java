package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.ApplicationType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ApplicationResponse {
    private Long id;
    private ApplicationType type;
    private String content;
    private LocalDate date;
    private Long userId;
    private Long petId;
}