package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import com.example.pet_shelter.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @PostMapping("/register")
    @Operation(summary = "Зарегистрировать волонтёра")
    public Volunteer register(@RequestParam Long userId, @RequestParam Long shelterId) {
        return volunteerService.registerVolunteer(userId, shelterId);
    }

    @GetMapping("/shelter/{shelterId}")
    @Operation(summary = "Список волонтёров приюта")
    public List<Volunteer> getByShelter(@PathVariable Long shelterId) {
        return volunteerService.getVolunteersByShelter(shelterId);
    }

    @PatchMapping("/{volunteerId}/status")
    @Operation(summary = "Изменить статус волонтёра")
    public Volunteer updateStatus(@PathVariable Long volunteerId, @RequestParam VolunteerStatus status) {
        return volunteerService.updateStatus(volunteerId, status);
    }
}
