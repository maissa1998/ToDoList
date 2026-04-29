package com.example.ToDoListBack.Controller;

import com.example.ToDoListBack.Dto.UserResponseDTO;
import com.example.ToDoListBack.Entity.Task;
import com.example.ToDoListBack.Service.Impl.TaskAIServiceImp;
import com.example.ToDoListBack.Service.Interface.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final TaskAIServiceImp taskAIService;
    private final TaskService taskService;

    public ChatController(TaskAIServiceImp taskAIService, TaskService taskService) {
        this.taskAIService = taskAIService;
        this.taskService = taskService;
    }

    @PostMapping("/recommend")
    public String recommend() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserResponseDTO user = (UserResponseDTO) auth.getPrincipal();

        List<Task> tasks = taskService.getTasksByUser(user.getId());

        List<String> taskNames = tasks.stream()
                .map(Task::getTitle) // or getName()
                .toList();

        return taskAIService.suggestNextTask(taskNames);
    }
}
