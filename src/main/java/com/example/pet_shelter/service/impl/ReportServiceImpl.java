package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Report;
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

    @Override
    public List<Report> getReportsByPet(Long petId) {
        return reportRepository.findByPetId(petId); // Предполагаем, что такой метод есть в репозитории
    }

    @Override
    public Report updateStatus(Long reportId, ReportStatus status) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(status);
        return reportRepository.save(report);
    }
}
