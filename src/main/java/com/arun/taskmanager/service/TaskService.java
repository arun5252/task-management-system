package com.arun.taskmanager.service;

import com.arun.taskmanager.entity.Task;
import com.arun.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        Task task = null;

        if(optional.isPresent()) {
            task = optional.get();
        } else {
            throw new RuntimeException("Task not found for id : " + id);
        }

        return task;
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }
}