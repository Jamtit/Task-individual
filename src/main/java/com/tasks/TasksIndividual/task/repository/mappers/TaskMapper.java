package com.tasks.TasksIndividual.task.repository.mappers;

import com.tasks.TasksIndividual.task.repository.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Task(
                resultSet.getInt("id"),
                resultSet.getString("task_name"),
                resultSet.getString("task_desc")
        );
    }
}
