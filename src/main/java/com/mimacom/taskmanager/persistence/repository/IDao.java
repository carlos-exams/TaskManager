package com.mimacom.taskmanager.persistence.repository;
import com.mimacom.taskmanager.persistence.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDao <T extends AbstractEntity> extends JpaRepository<T, Integer>{
    Optional<T> findById(Integer id);
}
