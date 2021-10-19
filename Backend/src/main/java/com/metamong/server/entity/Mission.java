package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Mission extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "education_id")
    Education education;

    int ordiering;

    String description;
}
