package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.enums.VolunteerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByShelterId(Long shelterId);  // Волонтёры приюта
    List<Volunteer> findByStatus(VolunteerStatus status);
    Optional<Volunteer> findByUserId(Long userId);// По статусу
}
