package com.metamong.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sent_user_id")
    private User sentUserId;

    @ManyToOne
    @JoinColumn(name = "recv_user_id")
    private User recvUserId;
    
    private String title;
    
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name="is_read", columnDefinition = "TINYINT", length=4)
    private int isRead;
}
