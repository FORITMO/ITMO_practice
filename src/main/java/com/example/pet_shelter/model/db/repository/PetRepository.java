package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status); // Поиск по статусу
}
