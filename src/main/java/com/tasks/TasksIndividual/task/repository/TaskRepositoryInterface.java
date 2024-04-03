package com.tasks.TasksIndividual.task.repository;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    List<Task> selectAllTasks();
    Optional<Task> selectTaskById(String id);
    void createTask(Task task);
    void deleteTaskById(String id);
    void updateTaskById(String id, Task task);
}
