package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.exception.ResourceNotFoundException;
import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.model.db.repository.PetRepository;
import com.example.pet_shelter.model.db.repository.ShelterRepository;
import com.example.pet_shelter.model.dto.request.ShelterRequest;
import com.example.pet_shelter.model.dto.response.ShelterResponse;
import com.example.pet_shelter.service.ShelterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional
    public ShelterResponse createShelter(ShelterRequest request) {
        if (shelterRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Приют с таким названием уже существует");
        }

        Shelter shelter = Shelter.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .build();

        Shelter savedShelter = shelterRepository.save(shelter);
        log.info("Created shelter with id: {}", savedShelter.getId());
        return convertToResponse(savedShelter);
    }

    @Override
    @Transactional(readOnly = true)
    public ShelterResponse getShelterById(Long id) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Приют не найден с ID: " + id));
        return convertToResponse(shelter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShelterResponse> getAllShelters() {
        return shelterRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShelterResponse> getAllShelters(Pageable pageable) {
        return shelterRepository.findAll(pageable)
                .map(this::convertToResponse);
    }

    @Override
    @Transactional
    public ShelterResponse updateShelter(Long id, ShelterRequest request) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Приют не найден с ID: " + id));

        if (!shelter.getName().equals(request.getName()) &&
                shelterRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Приют с таким названием уже существует");
        }

        shelter.setName(request.getName());
        shelter.setAddress(request.getAddress());
        shelter.setPhone(request.getPhone());

        Shelter updatedShelter = shelterRepository.save(shelter);
        log.info("Updated shelter with id: {}", id);
        return convertToResponse(updatedShelter);
    }

    @Override
    @Transactional
    public void deleteShelter(Long id) {
        if (!shelterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Приют не найден с ID: " + id);
        }


        List<Pet> pets = petRepository.findByShelterId(id);
        if (!pets.isEmpty()) {
            throw new IllegalStateException("Нельзя удалить приют с привязанными питомцами");
        }

        shelterRepository.deleteById(id);
        log.info("Deleted shelter with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShelterResponse> getSheltersByPhone(String phone) {
        return shelterRepository.findByPhoneContaining(phone).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ShelterResponse getShelterWithPets(Long id) {
        Shelter shelter = shelterRepository.findByIdWithPets(id)
                .orElseThrow(() -> new ResourceNotFoundException("Приют не найден с ID: " + id));

        ShelterResponse response = convertToResponse(shelter);
        response.setPetIds(shelter.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList()));

        return response;
    }

    @Override
    @Transactional
    public ShelterResponse addPetToShelter(Long shelterId, Long petId) {
        Shelter shelter = shelterRepository.findById(shelterId)
                .orElseThrow(() -> new ResourceNotFoundException("Приют не найден с ID: " + shelterId));

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Питомец не найден с ID: " + petId));

        pet.setShelter(shelter);
        petRepository.save(pet);

        return convertToResponse(shelter);
    }

    private ShelterResponse convertToResponse(Shelter shelter) {
        return ShelterResponse.builder()
                .id(shelter.getId())
                .name(shelter.getName())
                .address(shelter.getAddress())
                .phone(shelter.getPhone())
                .petsCount(shelter.getPets() != null ? shelter.getPets().size() : 0)
                .build();
    }
}