package com.example.pet_shelter.service;

import com.example.pet_shelter.model.dto.request.PetRequest;
import com.example.pet_shelter.model.dto.response.PetResponse;
import com.example.pet_shelter.model.enums.PetStatus;
import java.util.List;

public interface PetService {
    List<PetResponse> getPetsByStatus(PetStatus status);
    PetResponse createPet(PetRequest request);
    PetResponse updatePet(Long id, PetRequest request);
    void deletePet(Long id);
    PetResponse getPetById(Long id);
    List<PetResponse> getAllPets();
}