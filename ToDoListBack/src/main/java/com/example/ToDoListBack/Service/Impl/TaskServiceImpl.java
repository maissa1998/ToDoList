package com.example.ToDoListBack.Service.Impl;

import com.example.ToDoListBack.Entity.Task;
import com.example.ToDoListBack.Repository.TaskRepository;
import com.example.ToDoListBack.Service.Interface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) { return taskRepository.save(task); }
    @Override
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    public Optional<Task> getTaskById(Long id) { return taskRepository.findById(id); }
    public Task updateTask(Task task) { return taskRepository.save(task); }
    public void deleteTask(Long id) { taskRepository.deleteById(id); }
}
