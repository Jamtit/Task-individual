package com.tasks.TasksIndividual.task.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository implements TaskRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TaskDao> selectAllTasks(){
        String query = """
                SELECT * FROM task;
                """;
        return jdbcTemplate.queryForList(query, TaskDao.class);
    }

    @Override
    public TaskDao selectTaskById(int id){
        String query = """
                 SELECT * FROM task WHERE id=%d
                """.formatted(id);
        return jdbcTemplate.queryForObject(query, TaskDao.class);
    }
    @Override
    public void createTask(TaskDao task){
        String sql = """
                    INSERT INTO task VALUES(?, ?, ?)
                """;
        jdbcTemplate.update(sql, task.id(), task.taskName(), task.taskDesc());
    }
}
