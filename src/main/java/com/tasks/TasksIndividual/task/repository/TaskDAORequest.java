package com.tasks.TasksIndividual.task.repository;


public record TaskDAORequest(
        String id,
        String taskName,
        String taskDesc
) {}
