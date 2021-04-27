package com.mimacom.taskmanager.controllers;

import com.mimacom.taskmanager.dto.request.TaskCreateDto;
import com.mimacom.taskmanager.dto.request.TaskDeleteDto;
import com.mimacom.taskmanager.dto.request.TaskEditDto;
import com.mimacom.taskmanager.persistence.model.TaskStatus;
import com.mimacom.taskmanager.services.ITaskService;
import com.mimacom.taskmanager.services.ITaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mimacom.taskmanager.dto.response.TaskDto;
import com.mimacom.taskmanager.persistence.model.Task;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("task")
public class TaskController extends AbstractController<TaskDto, ITaskService> {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITaskStatusService taskStatusService;

    /*
    TODO - Having the id as integer is breaking swagger
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "id", value = "Task ID",required = true, dataType = "Integer")
    public TaskDto getTaskById(
            @ApiParam(value = "The task id", required = true) @PathVariable("id") Integer id){
        // Todo - try with RestException instead
        TaskDto taskDto = taskService.findById(id)
                .map(t -> new TaskDto(t))
                .orElseThrow(RuntimeException::new);

        return taskDto;
    }
    */

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> taskList = taskService.findAll();
        return convertListToDto(taskList);
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public void createTask(@RequestBody @Valid TaskCreateDto dto) {
        List <Task> taskList = taskService.findByName(dto.getName());
        Optional<TaskStatus> taskStatus = taskStatusService.findById(dto.getTaskStatusId());

        if(taskStatus.isPresent()) {
            // if there is no other task with the same name we proceed
            // its not a unique constraint, just done here because "why not?"
            if(taskList.isEmpty()){
                Task newTask = new Task(dto.getName(), dto.getDescription(), taskStatus.get());
                taskService.create(newTask);
            } else {
                // TODO - As part of the test the name is not unique in the database
                String error = "Cant create Task, name already exist["+dto.getName()+"]";
                logger.error(error);
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        error,
                        null);
            }
        } else {
            String error = "Provided Task Status Doesn´t exist["+dto.getTaskStatusId()+"]";
            logger.error(error);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error,
                    null);
        }
    }

    @PutMapping
    @ResponseStatus( HttpStatus.OK )
    public void editTask(@RequestBody @Valid TaskEditDto dto) {
        Optional<Task> task = taskService.findById(dto.getId());
        Optional<TaskStatus> taskStatus = Optional.empty();

        if(dto.getTaskStatusId() != null){
            taskStatus = taskStatusService.findById(dto.getTaskStatusId());
            if(!taskStatus.isPresent()){
                String error = "Provided Task Status Doesn´t exist["+dto.getTaskStatusId()+"]";
                logger.error(error);
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        error,
                        null);
            }
        }

        if(task.isPresent()) {
            Task unwrappedTask = task.get();
            unwrappedTask.setDescription(dto.getDescription());
            unwrappedTask.updateStatus(taskStatus);
            taskService.update(unwrappedTask);
        } else {
            String error = "Provided Task Doesn´t exist["+dto.getId()+"]";
            logger.error(error);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error,
                    null);
        }
    }

    @PostMapping(value="delete")
    @ResponseStatus( HttpStatus.OK )
    public void deleteTasks(@RequestBody @Valid TaskDeleteDto dto) {
        for(Integer id:dto.getIdSet()){
            Optional<Task> task = taskService.findById(id);
            if(task.isPresent()){
                taskService.delete(task.get());
            }else {
                logger.error("Cannot delete Task id["+id+"]");
            }
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
