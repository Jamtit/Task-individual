package com.tasks.TasksIndividual.task.dto.request;

public record TaskUpdateRequest(
        String id,
        String taskName,
        String taskDesc
) {
}
