package com.example.pet_shelter.service;

import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import java.util.List;

public interface VolunteerService {
    Volunteer registerVolunteer(Long userId, Long shelterId);
    List<Volunteer> getVolunteersByShelter(Long shelterId);
    Volunteer updateStatus(Long volunteerId, VolunteerStatus status);
}
