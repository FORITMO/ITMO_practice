package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.dto.request.ShelterRequest;
import com.example.pet_shelter.model.dto.response.ShelterResponse;
import com.example.pet_shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
@Tag(name = "Приюты", description = "Операции с приютами для животных")
public class ShelterController {

    private final ShelterService shelterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый приют")
    public ShelterResponse createShelter(
            @RequestBody @Valid ShelterRequest request) {
        log.info("Creating new shelter with name: {}", request.getName());
        return shelterService.createShelter(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить приют по ID")
    public ShelterResponse getShelterById(
            @PathVariable @Parameter(description = "ID приюта") Long id) {
        log.info("Getting shelter by id: {}", id);
        return shelterService.getShelterById(id);
    }

    @GetMapping
    @Operation(summary = "Получить все приюты")
    public List<ShelterResponse> getAllShelters() {
        log.info("Getting all shelters");
        return shelterService.getAllShelters();
    }

    @GetMapping("/page")
    @Operation(summary = "Получить приюты с пагинацией")
    public Page<ShelterResponse> getAllShelters(Pageable pageable) {
        log.info("Getting shelters with pagination: {}", pageable);
        return shelterService.getAllShelters(pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные приюта")
    public ShelterResponse updateShelter(
            @PathVariable @Parameter(description = "ID приюта") Long id,
            @RequestBody @Valid ShelterRequest request) {
        log.info("Updating shelter with id: {}", id);
        return shelterService.updateShelter(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить приют")
    public void deleteShelter(
            @PathVariable @Parameter(description = "ID приюта") Long id) {
        log.info("Deleting shelter with id: {}", id);
        shelterService.deleteShelter(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск приютов по телефону")
    public List<ShelterResponse> getSheltersByPhone(
            @RequestParam @Parameter(description = "Номер телефона") String phone) {
        log.info("Searching shelters by phone: {}", phone);
        return shelterService.getSheltersByPhone(phone);
    }

    @GetMapping("/{id}/with-pets")
    @Operation(summary = "Получить приют с информацией о питомцах")
    public ShelterResponse getShelterWithPets(
            @PathVariable @Parameter(description = "ID приюта") Long id) {
        log.info("Getting shelter with pets by id: {}", id);
        return shelterService.getShelterWithPets(id);
    }

    @PostMapping("/{shelterId}/pets/{petId}")
    @Operation(summary = "Добавить питомца в приют")
    public ShelterResponse addPetToShelter(
            @PathVariable @Parameter(description = "ID приюта") Long shelterId,
            @PathVariable @Parameter(description = "ID питомца") Long petId) {
        log.info("Adding pet {} to shelter {}", petId, shelterId);
        return shelterService.addPetToShelter(shelterId, petId);
    }
}