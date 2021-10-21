package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Education extends BaseEntity {

    private Integer duration;

    @Column(unique=true)
    private String education;

    @OneToMany(mappedBy = "education")
    private List<Certificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "education")
    private List<Mission> missions = new ArrayList<>();

}
