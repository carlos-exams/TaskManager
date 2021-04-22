package com.mimacom.taskmanager.persistence.repository;

import com.mimacom.taskmanager.persistence.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskDao extends IDao<Task> {
    List<Task> findByName(String name);
}
