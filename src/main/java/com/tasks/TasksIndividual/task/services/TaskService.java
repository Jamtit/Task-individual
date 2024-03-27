package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.repository.Task;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    List<Task> selectAllTasks();
    Task selectTaskById(int id) throws TaskNotFoundException;

}
