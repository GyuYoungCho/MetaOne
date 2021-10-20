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

    @OneToMany(mappedBy = "guestBook")
    private List<User> users = new ArrayList<>();

}
