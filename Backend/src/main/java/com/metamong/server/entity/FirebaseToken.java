package com.metamong.server.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "firebase_token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseToken extends BaseEntity {

    private String token;

    @CreatedDate
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
