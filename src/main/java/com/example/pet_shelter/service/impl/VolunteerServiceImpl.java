package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.model.db.entity.User;
import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.db.repository.ShelterRepository;
import com.example.pet_shelter.model.db.repository.UserRepository;
import com.example.pet_shelter.model.db.repository.VolunteerRepository;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import com.example.pet_shelter.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;

    @Override
    public Volunteer registerVolunteer(Long userId, Long shelterId) {
        User user = userRepository.findById(userId).orElseThrow();
        Shelter shelter = shelterRepository.findById(shelterId).orElseThrow(); // Находим приют

        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        volunteer.setShelter(shelter);
        volunteer.setStatus(VolunteerStatus.ACTIVE);
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getVolunteersByShelter(Long shelterId) {
        return volunteerRepository.findByShelterId(shelterId);
    }

    @Override
    public Volunteer updateStatus(Long volunteerId, VolunteerStatus status) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow();
        volunteer.setStatus(status);
        return volunteerRepository.save(volunteer);
    }
}

