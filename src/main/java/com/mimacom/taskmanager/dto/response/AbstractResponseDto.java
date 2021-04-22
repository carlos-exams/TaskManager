package com.mimacom.taskmanager.dto.response;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class AbstractResponseDto {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected  Integer id;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Date creationDate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Date updateDate;

    protected AbstractResponseDto (Integer id, Date creationDate, Date updateDate){
        setId(id);
        setCreationDate(creationDate);
        setUpdateDate(updateDate);
    }
}
