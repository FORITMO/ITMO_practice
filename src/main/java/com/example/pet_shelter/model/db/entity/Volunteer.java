package com.example.pet_shelter.model.db.entity;

import com.example.pet_shelter.model.enums.VolunteerStatus;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;  // Связь с пользователем

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;  // Приют, к которому прикреплён волонтёр

    @Enumerated(EnumType.STRING)
    private VolunteerStatus status;

    @OneToMany(mappedBy = "volunteer")
    private List<Report> reports;  // Отчёты волонтёра
}
