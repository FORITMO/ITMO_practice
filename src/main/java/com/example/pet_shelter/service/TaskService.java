package com.example.pet_shelter.service;

import com.example.pet_shelter.model.db.entity.Task;
import com.example.pet_shelter.model.enums.TaskStatus;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task assignToVolunteer(Long taskId, Long volunteerId);
    Task updateStatus(Long taskId, TaskStatus status);
    List<Task> getTasksByVolunteer(Long volunteerId);
}
