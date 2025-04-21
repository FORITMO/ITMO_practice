package com.example.pet_shelter.model.db.entity;

import com.example.pet_shelter.model.enums.TaskStatus;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; // NEW, IN_PROGRESS, COMPLETED

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;  // Назначенный волонтёр

    private LocalDateTime deadline;
    private LocalDateTime createdAt = LocalDateTime.now();
}
