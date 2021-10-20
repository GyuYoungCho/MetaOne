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

    /* 유저의 룸 번호 */
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    private String email;

    private String password;

    private String fileUrl;

    @Column(name="auth", columnDefinition = "TINYINT", length=4)
    private int auth;

    private String nickname;

    private String name;

    @Column(name="state", columnDefinition = "TINYINT", length=4)
    private int state;

    /* 유저의 증명서 */
    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates = new ArrayList<>();

    /* 유저의 쪽지 */
//    @OneToMany(mappedBy = "user")
//    private List<Message> messages = new ArrayList<>();

    /* 유저의 게시판 */
    @OneToMany(mappedBy = "user")
    private List<GuestBook> guestBooks = new ArrayList<>();

}
