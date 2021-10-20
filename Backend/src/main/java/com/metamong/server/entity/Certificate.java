package com.metamong.server.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Certificate extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="education_id")
    private Education education;

    private Date createAt;

    private Integer passTime;

    @Column(name="is_educated", columnDefinition = "TINYINT", length=4)
    private int isEducated;

    @Column(name="is_authenticated", columnDefinition = "TINYINT", length=4)
    private int isAuthenticated;

}
