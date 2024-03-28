package com.tasks.TasksIndividual.task.repository;

import com.tasks.TasksIndividual.task.repository.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task> selectTaskById(int id){
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
                    VALUES(:id, :task_name, :task_desc)
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", task.id())
                .addValue("task_name", task.taskName())
                .addValue("task_desc", task.taskDesc());
        namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void deleteTaskById(int id){
        String query = """
                    DELETE FROM task
                    WHERE id = :id
                """;
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void updateTaskById(int id, Task task){
        String query = """
                    UPDATE task
                    SET task_name = :task_name, task_desc = :task_desc
                    WHERE id = :id
                """;
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("task_desc", task.taskDesc())
                .addValue("task_name", task.taskName());

        namedParameterJdbcTemplate.update(query, params);
    }
}
