package com.example.pet_shelter.service;

import com.example.pet_shelter.model.dto.request.ShelterRequest;
import com.example.pet_shelter.model.dto.response.ShelterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShelterService {
    ShelterResponse createShelter(ShelterRequest request);
    ShelterResponse getShelterById(Long id);
    List<ShelterResponse> getAllShelters();
    ShelterResponse updateShelter(Long id, ShelterRequest request);
    void deleteShelter(Long id);
    List<ShelterResponse> getSheltersByPhone(String phone);
    Page<ShelterResponse> getAllShelters(Pageable pageable);
    ShelterResponse getShelterWithPets(Long id);
    ShelterResponse addPetToShelter(Long shelterId, Long petId);
}