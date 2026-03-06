package com.arun.taskmanager.controller;

import com.arun.taskmanager.entity.Task;
import com.arun.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listTasks", taskService.getAllTasks());
        return "index";
    }

    @GetMapping("/showNewTaskForm")
    public String showNewTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "new_task";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "update_task";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable(value = "id") Long id) {
        this.taskService.deleteTaskById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchTasks(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("listTasks", taskService.searchTasks(keyword));
        return "index";
    }

    @PostMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task) {
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/";
    }
}