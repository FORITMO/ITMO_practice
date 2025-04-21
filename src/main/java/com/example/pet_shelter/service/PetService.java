package com.example.pet_shelter.service;

import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.enums.PetStatus;
import java.util.List;

public interface PetService {
    List<Pet> getPetsByStatus(PetStatus status);
    Pet addPet(Pet pet);
}
