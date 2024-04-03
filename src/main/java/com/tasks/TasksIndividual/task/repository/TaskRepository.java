package com.tasks.TasksIndividual.task.repository;

import com.tasks.TasksIndividual.task.repository.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepository implements TaskRepositoryInterface{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TaskRepository(NamedParameterJdbcTemplate template){
        this.namedParameterJdbcTemplate = template;
    }

    @Override
    public List<Task> selectAllTasks(){
        String query = """
                    SELECT id, task_name, task_desc
                    FROM task
                """;
        return namedParameterJdbcTemplate.query(query, new TaskMapper());
    }

    @Override
    public Optional<Task> selectTaskById(String id){
        String query = """
                 SELECT id, task_name, task_desc
                 FROM task
                 WHERE id= :id
                """;
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.query(query, params, new TaskMapper())
                .stream()
                .findFirst();
    }

    @Override
    public void createTask(Task task){
        String query = """
                    INSERT INTO task
                    VALUES(:id, :taskName, :taskDesc)
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", task.id())
                .addValue("taskName", task.taskName())
                .addValue("taskDesc", task.taskDesc());
        namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void deleteTaskById(String id){
        String query = """
                    DELETE FROM task
                    WHERE id = :id
                """;
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void updateTaskById(String id, Task task){
        String query = """
                    UPDATE task
                    SET task_name = :taskName, task_desc = :taskDesc
                    WHERE id = :id
                """;
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("taskDesc", task.taskDesc())
                .addValue("taskName", task.taskName());

        namedParameterJdbcTemplate.update(query, params);
    }
}
