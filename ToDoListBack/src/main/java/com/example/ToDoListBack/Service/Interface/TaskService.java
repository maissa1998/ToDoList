package com.example.ToDoListBack.Service.Interface;

import com.example.ToDoListBack.Entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getTasksByUser(Long userId);
    Optional<Task> getTaskById(Long id);
    Task updateTask(Task task);
    void deleteTask(Long id);
}
