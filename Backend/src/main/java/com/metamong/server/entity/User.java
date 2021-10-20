package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    private String email;

    private String password;

    @Column(name="auth", columnDefinition = "TINYINT", length=4)
    private int auth;

    private String nickname;

    private String name;

    @Column(name="state", columnDefinition = "TINYINT", length=4)
    private int state;

    @OneToOne(mappedBy = "user")
    private Characters character;

    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Message> messages = new ArrayList<>();

    /* ������ �Խ��� */
    @OneToMany(mappedBy = "user")
    private List<GuestBook> guestBooks = new ArrayList<>();

}
