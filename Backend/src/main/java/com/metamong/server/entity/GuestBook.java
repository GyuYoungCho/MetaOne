package com.metamong.server.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBook extends BaseEntity{

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    private String content;

    /* 방명록을 작성한 유저 리스트 */
    @OneToMany(mappedBy = "guestBook")
    private List<User> users = new ArrayList<>();

}
