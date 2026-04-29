package com.example.ToDoListBack.Service.Impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAIServiceImp {
    private final ChatClient chatClient;

    public TaskAIServiceImp(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String suggestNextTask(List<String> tasks) {

        String prompt = """
            You are a productivity assistant.

            Here are the user's tasks:
            %s

            Return ONLY:
            - the next best task
            - short reason
            """.formatted(tasks);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
