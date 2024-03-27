package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.repository.Task;
import com.tasks.TasksIndividual.task.repository.TaskRepository;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> selectAllTasks(){
        return taskRepository.selectAllTasks();
    }

    @Override
    public Task selectTaskById(int id) throws TaskNotFoundException{
        Optional<Task> task = taskRepository.selectTaskById(id);
        return task.orElseThrow(() -> new TaskNotFoundException(id));
    }
}
