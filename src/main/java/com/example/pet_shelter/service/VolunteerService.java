package com.example.pet_shelter.service;

import com.example.pet_shelter.model.dto.request.VolunteerRequest;
import com.example.pet_shelter.model.dto.response.VolunteerResponse;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import java.util.List;

public interface VolunteerService {
    VolunteerResponse registerVolunteer(VolunteerRequest request);
    List<VolunteerResponse> getVolunteersByShelter(Long shelterId);
    VolunteerResponse updateStatus(Long volunteerId, VolunteerStatus status);
}