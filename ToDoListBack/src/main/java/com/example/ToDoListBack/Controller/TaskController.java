package com.example.ToDoListBack.Controller;

import com.example.ToDoListBack.Dto.UserResponseDTO;
import com.example.ToDoListBack.Entity.Task;
import com.example.ToDoListBack.Entity.User;
import com.example.ToDoListBack.Service.Interface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || authentication.getPrincipal() == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }

            UserResponseDTO loggedUser = (UserResponseDTO) authentication.getPrincipal();

            User userEntity = new User();
            userEntity.setId(loggedUser.getId());
            task.setUser(userEntity);

            Task createdTask = taskService.createTask(task);
            return ResponseEntity.ok(createdTask);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<?> getMyTasks() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserResponseDTO loggedUser = (UserResponseDTO) authentication.getPrincipal();

            List<Task> tasks = taskService.getTasksByUser(loggedUser.getId());
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            Optional<Task> task = taskService.getTaskById(id);

            if (task.isPresent()) {
                return ResponseEntity.ok(task.get());
            }

            return ResponseEntity.status(404).body(Map.of("error", "Task not found"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // UPDATE TASK
    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggleTaskCompletion(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserResponseDTO loggedUser = (UserResponseDTO) authentication.getPrincipal();

            User userEntity = new User();
            userEntity.setId(loggedUser.getId());

            // Fetch task from DB
            Optional<Task> taskOpt = taskService.getTaskById(id);
            if (taskOpt.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("error", "Task not found"));
            }

            Task task = taskOpt.get();

            if (!task.getUser().getId().equals(userEntity.getId())) {
                return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
            }

            // Toggle completion
            task.setCompleted(!task.isCompleted());
            Task updatedTask = taskService.updateTask(task);

            return ResponseEntity.ok(updatedTask);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE TASK
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
