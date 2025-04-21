package com.example.pet_shelter.model.db.entity;

import com.example.pet_shelter.model.enums.ApplicationType;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationType type;

    private String content;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
