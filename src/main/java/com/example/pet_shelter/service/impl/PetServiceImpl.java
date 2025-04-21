package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.enums.PetStatus;
import com.example.pet_shelter.model.db.repository.PetRepository;
import com.example.pet_shelter.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public List<Pet> getPetsByStatus(PetStatus status) {
        return petRepository.findByStatus(status);
    }

    @Override
    public Pet addPet(Pet pet) {
        pet.setCreatedAt(LocalDateTime.now());
        return petRepository.save(pet);
    }
}