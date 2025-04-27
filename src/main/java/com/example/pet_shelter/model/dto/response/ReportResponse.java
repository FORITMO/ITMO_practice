package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.ReportStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReportResponse {
    private Long id;
    private String text;
    private ReportStatus status;
    private LocalDateTime createdAt;
    private Long applicationId;
}