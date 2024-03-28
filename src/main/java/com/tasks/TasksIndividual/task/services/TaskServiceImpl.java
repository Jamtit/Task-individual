package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.dto.request.TaskRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.repository.Task;
import com.tasks.TasksIndividual.task.repository.TaskRepository;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<TaskResponse> selectAllTasks(){
        List<Task> tasks = taskRepository.selectAllTasks();
        return tasks.stream().map(this::convertTaskIntoResponse).toList();
    }

    @Override
    public TaskResponse selectTaskById(int id) throws TaskNotFoundException{
        Optional<Task> task = taskRepository.selectTaskById(id);
        if(task.isPresent()){
            return convertTaskIntoResponse(task.get());
        }
        throw new TaskNotFoundException(id);
    }

    @Override
    public void createTask(TaskRequest taskRequest){
        Task receivedTask = convertTaskRequestIntoTask(taskRequest);
        taskRepository.createTask(receivedTask);
    }

    @Override
    public void deleteTaskById(int id) throws TaskNotFoundException{
        Optional<Task> foundTask = taskRepository.selectTaskById(id);
        if(foundTask.isPresent()){
            taskRepository.deleteTaskById(id);
        }else{
            throw new TaskNotFoundException(id);
        }

    }

    @Override
    public void updateTaskById(int id, TaskRequest taskRequest) throws TaskNotFoundException{
        Optional<Task> foundTask = taskRepository.selectTaskById(id);
        Task receivedTask = convertTaskRequestIntoTask(taskRequest);
        if(foundTask.isPresent()){
         taskRepository.updateTaskById(id, receivedTask);
        }else{
            throw new TaskNotFoundException(id);
        }
    }


    private Task convertTaskRequestIntoTask(TaskRequest taskRequest){
        return taskRequest == null
                ? null
                : new Task(taskRequest.id(), taskRequest.task_name(), taskRequest.task_desc());
    }

    private TaskResponse convertTaskIntoResponse(Task task){
        return task == null
                ? null
                : new TaskResponse(task.taskName(), task.taskDesc());
    }
}
