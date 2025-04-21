package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Task;
import com.example.pet_shelter.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByVolunteerId(Long volunteerId);
    List<Task> findByStatus(TaskStatus status);
}
