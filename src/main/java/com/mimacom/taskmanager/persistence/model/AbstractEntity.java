package com.mimacom.taskmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EqualsAndHashCode()
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected  Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_date")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Date updateDate;

    protected AbstractEntity(){
        super();
    }

    @PrePersist
    protected void onCreate(){
        updateDate = creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updateDate = new Date();
    }
}
