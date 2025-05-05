package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Pet;
import com.example.pet_shelter.model.db.entity.Report;
import com.example.pet_shelter.model.db.repository.PetRepository;
import com.example.pet_shelter.model.db.repository.ReportRepository;
import com.example.pet_shelter.model.enums.ReportStatus;
import com.example.pet_shelter.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PetRepository petRepository;

    @Override
    public List<Report> getReportsByPet(Long petId) {
        return reportRepository.findByPetId(petId);
    }

    @Override
    public Report updateStatus(Long reportId, ReportStatus status) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(status);
        return reportRepository.save(report);
    }

    public Report createReport(Long petId, Report report) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        report.setPet(pet);
        return reportRepository.save(report);
    }
}
