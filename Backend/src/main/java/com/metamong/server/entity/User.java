package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    Room room;

    String email;
    String password;
    String fileUrl;

    @Column(name="auth", columnDefinition = "TINYINT", length=4)
    int auth;
    String nickname;
    String name;

    @Column(name="state", columnDefinition = "TINYINT", length=4)
    int state;
}
