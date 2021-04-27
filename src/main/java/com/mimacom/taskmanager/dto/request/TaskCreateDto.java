package com.mimacom.taskmanager.dto.request;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
public class TaskCreateDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    @Size(min=2, max=30)
    @NotNull(message = "Name cannot be null")
    protected String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Size(min=0, max=50)
    protected String description;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    @NotNull(message = "A task status Id must be provided")
    @Positive
    protected Integer taskStatusId;

    public TaskCreateDto() {
        super();
    }

    public TaskCreateDto(String name, String description, Integer taskStatusId) {
        this();
        setName(name);
        setDescription(description);
        setTaskStatusId(taskStatusId);
    }

}
