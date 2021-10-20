package com.metamong.server.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "firebase_token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseToken extends BaseEntity {

    private String token;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
