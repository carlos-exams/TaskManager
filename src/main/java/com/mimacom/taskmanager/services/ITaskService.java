package com.mimacom.taskmanager.services;

import com.mimacom.taskmanager.persistence.model.Task;
import java.util.List;

public interface ITaskService extends IService<Task>{
    List<Task> findByName(String name);
}
