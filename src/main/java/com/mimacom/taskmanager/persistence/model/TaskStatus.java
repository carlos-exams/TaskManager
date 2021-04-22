package com.mimacom.taskmanager.persistence.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
@Table(name="task_status")
public class TaskStatus extends AbstractEntity{
    @Column(name="name", nullable = false)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected String name;

    @Column(name="description", nullable = false)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String description;

    public TaskStatus() {
        super();
    }

    public TaskStatus(String name, String description) {
        this();
        this.setName(name);
        this.setDescription(description);
    }

}
