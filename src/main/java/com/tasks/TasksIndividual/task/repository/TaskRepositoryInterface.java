package com.tasks.TasksIndividual.task.repository;

import com.tasks.TasksIndividual.task.dto.response.TaskResponse;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    List<TaskDAOResponse> selectAllTasks();
    Optional<TaskDAOResponse> selectTaskById(String id);
    void createTask(TaskDAORequest task);
    void deleteTaskById(String id);
    void updateTaskById(String id, TaskDAORequest task);
}
