package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Application;
import com.example.pet_shelter.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Подать заявление (только для USER)")
    public Application createApplication(@RequestBody Application application) {
        return applicationService.create(application);
    }
}
