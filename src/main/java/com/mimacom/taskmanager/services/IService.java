package com.mimacom.taskmanager.services;

import com.mimacom.taskmanager.persistence.model.AbstractEntity;
import com.mimacom.taskmanager.persistence.model.Task;

import java.util.List;
import java.util.Optional;

public interface IService <T extends AbstractEntity>{
    public Optional<T> findById(Integer id);
    public List<T> findAll();
    public boolean create(T t);
    public boolean update(T t);
    public boolean delete(T t);
}
