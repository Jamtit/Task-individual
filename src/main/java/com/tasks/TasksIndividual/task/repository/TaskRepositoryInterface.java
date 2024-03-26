package com.tasks.TasksIndividual.task.repository;

import java.util.List;

public interface TaskRepositoryInterface {
    List<TaskDao> selectAllTasks();
    TaskDao selectTaskById(int id);
    void createTask(TaskDao task);
}
