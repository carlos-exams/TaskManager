package com.mimacom.taskmanager.controllers;

import com.mimacom.taskmanager.dto.response.TaskStatusDto;
import com.mimacom.taskmanager.persistence.model.TaskStatus;
import com.mimacom.taskmanager.services.ITaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("TaskStatus")
public class TaskStatusController extends AbstractController<TaskStatusDto, ITaskStatusService> {

    @Autowired
    private ITaskStatusService taskStatusService;

    @GetMapping
    public List<TaskStatusDto> getTaskStatuses() {
        List<TaskStatus> taskStatusList = taskStatusService.findAll();
        return convertListToDto(taskStatusList);
    }

    public List<TaskStatusDto> convertListToDto(List <TaskStatus> taskStatusesList) {
        return taskStatusesList.stream()
                .map(taskStatus -> new TaskStatusDto(taskStatus))
                .collect(Collectors.toList());
    }

    @Override
    public ITaskStatusService getService() {
        return taskStatusService;
    }
}
