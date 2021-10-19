package com.metamong.server.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class OauthProvider extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    String providerName;
}
