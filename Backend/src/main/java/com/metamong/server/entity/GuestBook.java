package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class GuestBook extends BaseEntity{

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
