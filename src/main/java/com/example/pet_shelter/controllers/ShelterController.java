package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Shelter;
import com.example.pet_shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
@Tag(name = "Приюты", description = "Управление приютами")
public class ShelterController {
    private final ShelterService shelterService;

    @GetMapping
    @Operation(summary = "Получить список всех приютов")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }
}
