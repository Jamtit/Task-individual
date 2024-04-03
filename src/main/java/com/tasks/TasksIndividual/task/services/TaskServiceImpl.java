package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.dto.request.TaskPostRequest;
import com.tasks.TasksIndividual.task.dto.request.TaskUpdateRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.repository.Task;
import com.tasks.TasksIndividual.task.repository.TaskRepository;
import com.tasks.TasksIndividual.task.services.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponse> selectAllTasks() {
        List<Task> tasks = taskRepository.selectAllTasks();
        return tasks.stream().map(this::convertTaskIntoResponse).toList();
    }

    @Override
    public TaskResponse selectTaskById(String id) throws TaskNotFoundException {
        Optional<Task> task = taskRepository.selectTaskById(id);
        if (task.isPresent()) {
            return convertTaskIntoResponse(task.get());
        }
        throw new TaskNotFoundException(id);
    }

    @Override
    public void createTask(TaskPostRequest taskPostRequest) {
        Task receivedTask = convertTaskRequestIntoTask(taskPostRequest);
        taskRepository.createTask(receivedTask);
    }

    @Override
    public void deleteTaskById(String id) throws TaskNotFoundException {
        Optional<Task> foundTask = taskRepository.selectTaskById(id);
        if (foundTask.isPresent()) {
            taskRepository.deleteTaskById(id);
        } else {
            throw new TaskNotFoundException(id);
        }

    }

    @Override
    public void updateTaskById(String id, TaskUpdateRequest taskUpdateRequest) throws TaskNotFoundException {
        Optional<Task> foundTask = taskRepository.selectTaskById(id);
        Task receivedTask = convertTaskRequestIntoTask(taskUpdateRequest);
        if (foundTask.isPresent()) {
            taskRepository.updateTaskById(id, receivedTask);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    private Task convertTaskRequestIntoTask( TaskPostRequest taskPostRequest) {
        return taskPostRequest == null
                ? null
                : new Task(UUID.randomUUID().toString(), taskPostRequest.taskName(), taskPostRequest.taskDesc());
    }

    private Task convertTaskRequestIntoTask( TaskUpdateRequest taskUpdateRequest) {
        return taskUpdateRequest == null
                ? null
                : new Task(taskUpdateRequest.id(), taskUpdateRequest.taskName(), taskUpdateRequest.taskDesc());
    }

    private TaskResponse convertTaskIntoResponse(Task task) {
        return task == null
                ? null
                : new TaskResponse(task.taskName(), task.taskDesc());
    }
}
