package com.example.pet_shelter.service;

import com.example.pet_shelter.model.db.entity.Report;
import com.example.pet_shelter.model.enums.ReportStatus;

import java.util.List;

public interface ReportService {
    List<Report> getReportsByPet(Long petId);
    Report updateStatus(Long reportId, ReportStatus status);
    Report createReport(Long petId,Report report);
}
