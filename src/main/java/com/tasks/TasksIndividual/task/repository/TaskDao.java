package com.tasks.TasksIndividual.task.repository;

public record TaskDao(
        int id,
        String taskName,
        String taskDesc
) {}
