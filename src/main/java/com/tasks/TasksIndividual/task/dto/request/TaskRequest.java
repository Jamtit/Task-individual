package com.tasks.TasksIndividual.task.dto.request;

import lombok.Data;

public record TaskRequest(
        int id,
        String task_name,
        String task_desc
) {
}
