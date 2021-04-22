package com.mimacom.taskmanager.dto.response;

import com.mimacom.taskmanager.persistence.model.Task;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
public class TaskDto extends AbstractResponseDto{
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String description;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String statusName;

    public TaskDto() {
        super();
    }

    public TaskDto(Task task) {
        super(task.getId(), task.getCreationDate(), task.getUpdateDate());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setStatusName(task.getStatus().getName());
    }
}
