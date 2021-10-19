package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
public class Education extends BaseEntity {

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date duration;

    private String education;

}
