package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private Long volunteerId;
}