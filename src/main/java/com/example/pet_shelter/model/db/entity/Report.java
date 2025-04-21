package com.example.pet_shelter.model.db.entity;

import com.example.pet_shelter.model.enums.ReportStatus;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;  // Текст отчёта
    private LocalDate date;      // Дата создания

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;  // Автор отчёта

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;  // Животное, по которому отчёт
}
