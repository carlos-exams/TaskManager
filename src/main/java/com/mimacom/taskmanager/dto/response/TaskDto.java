package com.mimacom.taskmanager.dto.response;

import com.mimacom.taskmanager.persistence.model.Task;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
public class TaskDto extends AbstractResponseDto{
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @NonNull
    protected String name;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    protected String description;

    public TaskDto() {
        super();
    }

    public TaskDto(Task task) {
        super(task.getId(), task.getCreationDate(), task.getUpdateDate());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
    }
}
