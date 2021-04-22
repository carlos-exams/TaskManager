package com.mimacom.taskmanager.services.impl;

import com.mimacom.taskmanager.persistence.model.AbstractEntity;
import com.mimacom.taskmanager.persistence.repository.IDao;
import com.mimacom.taskmanager.services.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractService <T extends AbstractEntity> implements IService <T> {
    protected abstract IDao getDao();

    @Override
    public Optional<T> findById(Integer id){
        return getDao().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public boolean create(T t) {
        try {
            getDao().save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            getDao().save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
