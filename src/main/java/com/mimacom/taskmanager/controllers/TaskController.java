package com.mimacom.taskmanager.controllers;

import com.mimacom.taskmanager.dto.request.CreateTaskDto;
import com.mimacom.taskmanager.services.ITaskService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mimacom.taskmanager.dto.response.TaskDto;
import com.mimacom.taskmanager.persistence.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/task")
public class TaskController extends AbstractController<TaskDto, ITaskService> {

    @Autowired
    private ITaskService taskService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskDto getTaskById(@PathVariable Integer id){
        // Todo - try with notfoundexception instead
        TaskDto taskDto = taskService.findById(id)
                .map(t -> new TaskDto(t))
                .orElseThrow(RuntimeException::new);

        return taskDto;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        List<Task> taskList = taskService.findAll();
        return convertListToDto(taskList);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus( HttpStatus.OK )
    public void createTask(@RequestBody CreateTaskDto dto) {
        List <Task> taskList = taskService.findByName(dto.getName());

        // if there is no other task with the same name we proceed
        // its not a unique constraint, just done here because "why not?"
        if(taskList.isEmpty()){
            Task newTask = new Task(dto.getName(), dto.getDescription());
            taskService.create(newTask);
        } else {
            new RuntimeException("Cant create the task as the name already exists");
        }

    }


    public List<TaskDto> convertListToDto(List <Task> taskList) {
        return taskList.stream()
                .map(task -> new TaskDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public ITaskService getService() {
        return taskService;
    }
}
