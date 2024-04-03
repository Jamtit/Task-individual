package com.tasks.TasksIndividual.task.services;

import com.tasks.TasksIndividual.task.dto.request.TaskPostRequest;
import com.tasks.TasksIndividual.task.dto.request.TaskUpdateRequest;
import com.tasks.TasksIndividual.task.dto.response.TaskResponse;
import com.tasks.TasksIndividual.task.repository.TaskDAORequest;
import com.tasks.TasksIndividual.task.repository.TaskDAOResponse;
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
        List<TaskDAOResponse> tasks = taskRepository.selectAllTasks();
        return tasks.stream().map(this::convertDaoResponseIntoTaskResponse).toList();
    }

    @Override
    public TaskResponse selectTaskById(String id) throws TaskNotFoundException {
        Optional<TaskDAOResponse> task = taskRepository.selectTaskById(id);
        if (task.isPresent()) {
            return convertDaoResponseIntoTaskResponse(task.get());
        }
        throw new TaskNotFoundException(id);
    }

    @Override
    public void createTask(TaskPostRequest taskPostRequest) {
        TaskDAORequest receivedTask = convertClientRequestIntoDaoRequest(taskPostRequest);
        taskRepository.createTask(receivedTask);
    }

    @Override
    public void deleteTaskById(String id) throws TaskNotFoundException {
        Optional<TaskDAOResponse> foundTask = taskRepository.selectTaskById(id);
        if (foundTask.isPresent()) {
            taskRepository.deleteTaskById(id);
        } else {
            throw new TaskNotFoundException(id);
        }

    }

    @Override
    public void updateTaskById(String id, TaskUpdateRequest taskUpdateRequest) throws TaskNotFoundException {
        Optional<TaskDAOResponse> foundTask = taskRepository.selectTaskById(id);
        TaskDAORequest receivedTask = convertTaskDAORequestIntoTaskDAOResponse(taskUpdateRequest);
        if (foundTask.isPresent()) {
            taskRepository.updateTaskById(id, receivedTask);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    private TaskDAORequest convertClientRequestIntoDaoRequest(TaskPostRequest taskPostRequest) {
        return taskPostRequest == null
                ? null
                : new TaskDAORequest(UUID.randomUUID().toString(), taskPostRequest.taskName(), taskPostRequest.taskDesc());
    }

    private TaskDAORequest convertTaskDAORequestIntoTaskDAOResponse(TaskUpdateRequest taskUpdateRequest) {
        return taskUpdateRequest == null
                ? null
                : new TaskDAORequest(taskUpdateRequest.id(), taskUpdateRequest.taskName(), taskUpdateRequest.taskDesc());
    }

    private TaskResponse convertDaoResponseIntoTaskResponse(TaskDAOResponse task) {
        return task == null
                ? null
                : new TaskResponse(task.taskName(), task.taskDesc());
    }
}
