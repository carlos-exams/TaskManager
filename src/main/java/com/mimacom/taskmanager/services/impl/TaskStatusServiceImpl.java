package com.mimacom.taskmanager.services.impl;


import com.mimacom.taskmanager.persistence.model.TaskStatus;
import com.mimacom.taskmanager.persistence.repository.IDao;
import com.mimacom.taskmanager.persistence.repository.ITaskDao;
import com.mimacom.taskmanager.persistence.repository.ITaskStatusDao;
import com.mimacom.taskmanager.services.ITaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusServiceImpl extends AbstractService<TaskStatus> implements ITaskStatusService {
    @Autowired
    private ITaskStatusDao dao;

    @Override
    protected IDao getDao() {
        return dao;
    }

    @Override
    public List<TaskStatus> findByName(String name) {
        return dao.findByName(name);
    }
}
