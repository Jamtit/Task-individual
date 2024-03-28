package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.dto.request.TaskRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    List<TaskResponse> selectAllTasks();
    TaskResponse selectTaskById(int id) throws TaskNotFoundException;
    void createTask(TaskRequest taskRequest);
    void deleteTaskById(int id) throws  TaskNotFoundException;
    void updateTaskById(int id, TaskRequest taskRequest) throws TaskNotFoundException;
}
