package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.exception.ValidationException;
import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.model.db.entity.User;
import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.db.repository.ShelterRepository;
import com.example.pet_shelter.model.db.repository.UserRepository;
import com.example.pet_shelter.model.db.repository.VolunteerRepository;
import com.example.pet_shelter.model.dto.request.VolunteerRequest;
import com.example.pet_shelter.model.dto.response.VolunteerResponse;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import com.example.pet_shelter.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;

    @Override
    @Transactional
    public VolunteerResponse registerVolunteer(VolunteerRequest request) {

        validateVolunteerRequest(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ValidationException("Пользователь с ID " + request.getUserId() + " не найден"));

        Shelter shelter = shelterRepository.findById(request.getShelterId())
                .orElseThrow(() -> new ValidationException("Приют с ID " + request.getShelterId() + " не найден"));


        volunteerRepository.findByUserId(request.getUserId()).ifPresent(v -> {
            throw new ValidationException("Пользователь уже зарегистрирован как волонтер");
        });

        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        volunteer.setShelter(shelter);
        volunteer.setStatus(Optional.ofNullable(request.getStatus()).orElse(VolunteerStatus.ACTIVE));

        Volunteer savedVolunteer = volunteerRepository.save(volunteer);
        return mapToResponse(savedVolunteer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VolunteerResponse> getVolunteersByShelter(Long shelterId) {
        if (shelterId == null || shelterId <= 0) {
            throw new ValidationException("Неверный ID приюта");
        }

        return volunteerRepository.findByShelterId(shelterId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VolunteerResponse updateStatus(Long volunteerId, VolunteerStatus status) {
        if (volunteerId == null || volunteerId <= 0) {
            throw new ValidationException("Неверный ID волонтера");
        }
        if (status == null) {
            throw new ValidationException("Статус не может быть null");
        }

        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ValidationException("Волонтер с ID " + volunteerId + " не найден"));

        volunteer.setStatus(status);
        Volunteer updatedVolunteer = volunteerRepository.save(volunteer);
        return mapToResponse(updatedVolunteer);
    }

    private void validateVolunteerRequest(VolunteerRequest request) {
        if (request == null) {
            throw new ValidationException("Запрос не может быть null");
        }

    }

    private VolunteerResponse mapToResponse(Volunteer volunteer) {
        return VolunteerResponse.builder()
                .id(volunteer.getId())
                .fullName(volunteer.getUser().getFullName())
                .phone(volunteer.getUser().getPhone())
                .status(volunteer.getStatus())
                .shelterId(volunteer.getShelter().getId())
                .build();
    }
}