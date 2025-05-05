package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Report;
import com.example.pet_shelter.model.enums.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByPetId(Long petId);
    List<Report> findByStatus(ReportStatus status);
}
