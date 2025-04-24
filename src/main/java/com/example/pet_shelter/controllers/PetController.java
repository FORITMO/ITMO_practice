package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.dto.request.PetRequest;
import com.example.pet_shelter.model.enums.PetStatus;
import com.example.pet_shelter.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/status/{status}")
    @Operation(summary = "Поиск животных по статусу (LOST, FOUND, IN_SHELTER)")
    public List<Pet> getByStatus(@PathVariable PetStatus status) {
        return petService.getPetsByStatus(status);
    }

    @PostMapping
    @Operation(summary = "Добавить новое животное (только для SHELTER_ADMIN)")
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @PostMapping
    public Pet addPet(@Valid @RequestBody PetRequest request) {
        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setStatus(request.getStatus());
        return petService.addPet(pet);
    }

}
