package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Characters extends BaseEntity{

    private String fileUrl;

    private String name;

}
