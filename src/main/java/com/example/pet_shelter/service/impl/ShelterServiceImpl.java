package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.model.db.repository.ShelterRepository;
import com.example.pet_shelter.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll(); // Стандартный метод JpaRepository
    }
}
