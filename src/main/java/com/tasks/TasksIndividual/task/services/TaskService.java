package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.dto.request.TaskPostRequest;
import com.tasks.TasksIndividual.task.dto.request.TaskUpdateRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    List<TaskResponse> selectAllTasks();
    TaskResponse selectTaskById(String id) throws TaskNotFoundException;
    void createTask(TaskPostRequest taskPostRequest);
    void deleteTaskById(String id) throws  TaskNotFoundException;
    void updateTaskById(String id, TaskUpdateRequest taskUpdateRequest) throws TaskNotFoundException;
}
