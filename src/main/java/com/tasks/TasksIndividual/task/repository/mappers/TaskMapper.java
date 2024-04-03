package com.tasks.TasksIndividual.task.repository.mappers;

import com.tasks.TasksIndividual.task.repository.TaskDAORequest;
import com.tasks.TasksIndividual.task.repository.TaskDAOResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<TaskDAOResponse> {
    @Override
    public TaskDAOResponse mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new TaskDAOResponse(
                resultSet.getString("id"),
                resultSet.getString("task_name"),
                resultSet.getString("task_desc")
        );
    }
}
