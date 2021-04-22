package com.mimacom.taskmanager.services;

import com.mimacom.taskmanager.persistence.model.TaskStatus;
import java.util.List;

public interface ITaskStatusService extends IService<TaskStatus>{
    List<TaskStatus> findByName(String name);
}
