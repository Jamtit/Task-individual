package com.tasks.TasksIndividual.task.controller;

import com.tasks.TasksIndividual.task.repository.Task;
import com.tasks.TasksIndividual.task.services.TaskServiceImpl;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService){
        this.taskService = taskService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        return taskService.selectAllTasks();
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable int id) throws TaskNotFoundException {
        return taskService.selectTaskById(id);
    }

    @PostMapping()
    public
}
