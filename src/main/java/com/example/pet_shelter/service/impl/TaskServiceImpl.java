package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Task;
import com.example.pet_shelter.model.db.entity.Volunteer;
import com.example.pet_shelter.model.db.repository.TaskRepository;
import com.example.pet_shelter.model.db.repository.VolunteerRepository;
import com.example.pet_shelter.model.enums.TaskStatus;
import com.example.pet_shelter.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public Task createTask(Task task) {
        task.setStatus(TaskStatus.NEW);
        return taskRepository.save(task);
    }

    @Override
    public Task assignToVolunteer(Long taskId, Long volunteerId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow();
        task.setVolunteer(volunteer);
        task.setStatus(TaskStatus.IN_PROGRESS);
        return taskRepository.save(task);
    }

    @Override
    public Task updateStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByVolunteer(Long volunteerId) {
        return taskRepository.findByVolunteerId(volunteerId);
    }
}
