package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Room extends BaseEntity {

    String name;
    int maxPopulation;
}
