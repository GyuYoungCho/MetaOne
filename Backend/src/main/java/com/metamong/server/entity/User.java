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
    
    @Column(name="tutorial", columnDefinition = "TINYINT", length=4)
    private int tutorial;

    public User(String email, String password, String name, String nickname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    public User() {
        super();
    }

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters character;

    @OneToMany(mappedBy = "user")
    private List<GuestBook> guestBooks = new ArrayList<>();

}
