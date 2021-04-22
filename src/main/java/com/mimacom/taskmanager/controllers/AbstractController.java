package com.mimacom.taskmanager.controllers;

import com.mimacom.taskmanager.dto.response.AbstractResponseDto;
import com.mimacom.taskmanager.persistence.model.AbstractEntity;
import com.mimacom.taskmanager.services.IService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Optional;


public abstract class AbstractController <T extends AbstractResponseDto, S extends IService>{
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public abstract S getService();
}
