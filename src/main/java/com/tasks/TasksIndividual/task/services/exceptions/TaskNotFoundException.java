package com.tasks.TasksIndividual.task.services.exceptions;

public class TaskNotFoundException extends TaskException{
    public TaskNotFoundException(int id){
        super("Cannot find a task with id: " + id);
    }
}
