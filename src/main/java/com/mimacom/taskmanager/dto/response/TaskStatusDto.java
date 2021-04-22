package com.mimacom.taskmanager.dto.response;

import com.mimacom.taskmanager.persistence.model.TaskStatus;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
public class TaskStatusDto extends AbstractResponseDto{
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String description;

    public TaskStatusDto() {
        super();
    }

    public TaskStatusDto(TaskStatus taskStatus) {
        super(taskStatus.getId(), taskStatus.getCreationDate(), taskStatus.getUpdateDate());
        this.setName(taskStatus.getName());
        this.setDescription(taskStatus.getDescription());
    }
}
