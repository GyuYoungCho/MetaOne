package com.metamong.server.entity;

import com.metamong.server.dto.GuestBookDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "guest_book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestBook extends BaseEntity{

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    private String content;


    @OneToMany(mappedBy = "guestBook")
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public GuestBookDto convertToDto(){
        return GuestBookDto.builder()
                .id(id)
                .userId(this.user.id)
                .createAt(this.createAt)
                .content(this.content)
                .build();
    }
}
