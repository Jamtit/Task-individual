package com.tasks.TasksIndividual.task.repository;


public record TaskDAOResponse(
        String id,
        String taskName,
        String taskDesc
) {}
