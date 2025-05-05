package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Report;
import com.example.pet_shelter.model.enums.ReportStatus;
import com.example.pet_shelter.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    @Operation(summary = "Создать новый отчёт")
    public Report createReport(
            @RequestParam Long petId,
            @RequestBody Report report
    ) {
        log.info("Creating report for pet: {}", petId);
        return reportService.createReport(petId, report);
    }

    @GetMapping("/pet/{petId}")
    @Operation(summary = "Отчёты по животному")
    public List<Report> getByPet(@PathVariable Long petId) {
        return reportService.getReportsByPet(petId);
    }

    @PatchMapping("/{reportId}/status")
    @Operation(summary = "Изменить статус отчёта")
    public Report updateStatus(@PathVariable Long reportId, @RequestParam ReportStatus status) {
        return reportService.updateStatus(reportId, status);
    }
}
