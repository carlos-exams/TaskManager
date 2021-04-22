package com.mimacom.taskmanager.persistence.repository;

import com.mimacom.taskmanager.persistence.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskStatusDao extends IDao<TaskStatus> {
    List<TaskStatus> findByName(String name);
}
