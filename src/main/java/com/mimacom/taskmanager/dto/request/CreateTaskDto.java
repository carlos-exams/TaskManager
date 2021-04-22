package com.mimacom.taskmanager.dto.request;

import com.mimacom.taskmanager.dto.response.AbstractResponseDto;
import com.mimacom.taskmanager.persistence.model.Task;
import lombok.*;

@RequiredArgsConstructor
public class CreateTaskDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String description;

    public CreateTaskDto() {
        super();
    }

}
