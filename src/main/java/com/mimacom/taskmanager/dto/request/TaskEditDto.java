package com.mimacom.taskmanager.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TaskEditDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    @NotNull
    @Positive
    protected Integer id;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Size(min=0, max=50)
    protected String description;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Positive
    protected Integer taskStatusId;

    public TaskEditDto() {
        super();
    }

    public TaskEditDto(Integer id, String description, Integer taskStatusId) {
        this();
        this.id = id;
        this.description = description;
        this.taskStatusId = taskStatusId;
    }
}
