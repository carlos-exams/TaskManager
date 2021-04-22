package com.mimacom.taskmanager.dto.request;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
public class CreateTaskDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    @NotNull
    @Size(min=2, max=30)
    protected String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Size(min=0, max=50)
    protected String description;

    public CreateTaskDto() {
        super();
    }

}
