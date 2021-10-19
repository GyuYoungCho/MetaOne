package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Getter
@Setter
public class OauthProvider extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String providerName;
}
