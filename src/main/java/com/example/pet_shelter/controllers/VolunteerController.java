package com.example.pet_shelter.controllers;

import com.example.pet_shelter.exception.ValidationException;
import com.example.pet_shelter.model.dto.request.VolunteerRequest;
import com.example.pet_shelter.model.dto.response.VolunteerResponse;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import com.example.pet_shelter.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @PostMapping("/register")
    @Operation(summary = "Зарегистрировать волонтёра")
    @ResponseStatus(HttpStatus.CREATED)
    public VolunteerResponse register(@RequestBody @Validated VolunteerRequest request) {
        try {
            return volunteerService.registerVolunteer(request);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/shelter/{shelterId}")
    @Operation(summary = "Список волонтёров приюта")
    public List<VolunteerResponse> getByShelter(@PathVariable Long shelterId) {
        try {
            return volunteerService.getVolunteersByShelter(shelterId);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PatchMapping("/{volunteerId}/status")
    @Operation(summary = "Изменить статус волонтёра")
    public VolunteerResponse updateStatus(
            @PathVariable Long volunteerId,
            @RequestParam VolunteerStatus status) {
        try {
            return volunteerService.updateStatus(volunteerId, status);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}