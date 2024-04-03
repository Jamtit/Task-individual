package com.tasks.TasksIndividual.task.controller;

import com.tasks.TasksIndividual.task.dto.request.TaskPostRequest;
import com.tasks.TasksIndividual.task.dto.request.TaskUpdateRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.services.TaskServiceImpl;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<TaskResponse> getAllTasks(){
        return taskService.selectAllTasks();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTaskById(@PathVariable String id) throws TaskNotFoundException {
        return taskService.selectTaskById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTask(@RequestBody TaskPostRequest taskPostRequest){
        taskService.createTask(taskPostRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable String id) throws TaskNotFoundException{
        taskService.deleteTaskById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTaskById(@PathVariable String id, @RequestBody TaskUpdateRequest taskUpdateRequest) throws TaskNotFoundException {
        taskService.updateTaskById(id, taskUpdateRequest);
    }
}
