package com.tasks.TasksIndividual.task.repository;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    List<Task> selectAllTasks();
    Optional<Task> selectTaskById(int id);
    void createTask(Task task);
    void deleteTaskById(int id);
    void updateTaskById(int id, Task task);
}
