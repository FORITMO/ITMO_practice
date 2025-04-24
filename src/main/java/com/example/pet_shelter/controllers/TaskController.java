package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.db.entity.Task;
import com.example.pet_shelter.model.enums.TaskStatus;
import com.example.pet_shelter.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Создать задачу")
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PatchMapping("/{taskId}/assign")
    @Operation(summary = "Назначить задачу волонтёру")
    public Task assign(@PathVariable Long taskId, @RequestParam Long volunteerId) {
        return taskService.assignToVolunteer(taskId, volunteerId);
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Список задач волонтёра")
    public List<Task> getByVolunteer(@PathVariable Long volunteerId) {
        return taskService.getTasksByVolunteer(volunteerId);
    }
}
