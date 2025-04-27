package com.example.pet_shelter.model.dto.response;

import com.example.pet_shelter.model.enums.VolunteerStatus;
import lombok.Data;
import java.util.List;

@Data
public class VolunteerResponse {
    private Long id;
    private String fullName;
    private String phone;
    private VolunteerStatus status;
    private List<Long> taskIds;
}