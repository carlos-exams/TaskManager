package com.mimacom.taskmanager.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
@Table(name="task")
public class Task extends AbstractEntity{
    @Column(name="name", nullable = false)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected String name;

    @Column(name="description", nullable = false)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    protected TaskStatus status;

    public Task() {
        super();
    }

    public Task(String name, String description, TaskStatus taskStatus) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setStatus(taskStatus);
    }

    public Task(Integer id, Date creationDate, Date updateDate, String name, String description, TaskStatus taskStatus) {
        super(id, creationDate, updateDate);
        this.setName(name);
        this.setDescription(description);
        this.setStatus(taskStatus);
    }

    public void updateStatus(Optional<TaskStatus> taskStatus) {
        if(taskStatus.isPresent()){
            this.setStatus(taskStatus.get());
        }
    }
}
