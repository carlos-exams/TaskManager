package com.mimacom.taskmanager.services.impl;

import com.mimacom.taskmanager.persistence.model.Task;
import com.mimacom.taskmanager.persistence.repository.IDao;
import com.mimacom.taskmanager.persistence.repository.ITaskDao;
import com.mimacom.taskmanager.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends AbstractService<Task> implements ITaskService {
    @Autowired
    private ITaskDao dao;

    @Override
    protected IDao getDao() {
        return dao;
    }

    @Override
    public List<Task> findByName(String name) {
        return dao.findByName(name);
    }
}
