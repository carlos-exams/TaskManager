package com.mimacom.taskmanager.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class TaskDeleteDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    @NotNull
    protected Set<Integer> idSet;

    public TaskDeleteDto() {
        super();
    }
}
