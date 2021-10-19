package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sent_user_id")
    private User sentUserId;

    @ManyToOne
    @JoinColumn(name = "recv_user_id")
    private User recvUserId;

    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name="is_read", columnDefinition = "TINYINT", length=4)
    private int isRead;
}
