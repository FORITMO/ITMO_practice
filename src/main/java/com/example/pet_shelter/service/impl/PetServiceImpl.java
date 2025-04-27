package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.exception.ResourceNotFoundException;
import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.model.db.repository.PetRepository;
import com.example.pet_shelter.model.db.repository.ShelterRepository;
import com.example.pet_shelter.model.dto.request.PetRequest;
import com.example.pet_shelter.model.dto.response.PetResponse;
import com.example.pet_shelter.model.enums.PetStatus;
import com.example.pet_shelter.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getPetsByStatus(PetStatus status) {
        return petRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PetResponse createPet(PetRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelterId())
                .orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id: " + request.getShelterId()));

        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setDescription(request.getDescription());
        pet.setStatus(request.getStatus());
        pet.setShelter(shelter);
        pet.setCreatedAt(LocalDateTime.now());

        Pet savedPet = petRepository.save(pet);
        return convertToResponse(savedPet);
    }

    @Override
    @Transactional(readOnly = true)
    public PetResponse getPetById(Long id) {
        return petRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }

    @Override
    @Transactional
    public PetResponse updatePet(Long id, PetRequest request) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));

        Shelter shelter = shelterRepository.findById(request.getShelterId())
                .orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id: " + request.getShelterId()));

        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setDescription(request.getDescription());
        pet.setStatus(request.getStatus());
        pet.setShelter(shelter);

        Pet updatedPet = petRepository.save(pet);
        return convertToResponse(updatedPet);
    }

    @Override
    @Transactional
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        petRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getAllPets() {
        return petRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private PetResponse convertToResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .description(pet.getDescription())
                .status(pet.getStatus())
                .shelterId(pet.getShelter().getId())
                .shelterName(pet.getShelter().getName())
                .createdAt(pet.getCreatedAt())
                .build();
    }
}