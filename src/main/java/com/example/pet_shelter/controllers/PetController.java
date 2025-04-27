package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.dto.request.PetRequest;
import com.example.pet_shelter.model.dto.response.PetResponse;
import com.example.pet_shelter.model.enums.PetStatus;
import com.example.pet_shelter.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping
    @Operation(summary = "Получить всех питомцев")
    public List<PetResponse> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить питомца по ID")
    public PetResponse getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Получить питомцев по статусу")
    public List<PetResponse> getPetsByStatus(@PathVariable PetStatus status) {
        return petService.getPetsByStatus(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать нового питомца")
    public PetResponse createPet(@RequestBody @Valid PetRequest request) {
        return petService.createPet(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные питомца")
    public PetResponse updatePet(@PathVariable Long id, @RequestBody @Valid PetRequest request) {
        return petService.updatePet(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить питомца")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}