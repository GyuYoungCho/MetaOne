package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
@Getter
@Setter
public class Education extends BaseEntity {

    Time duration;

    String education;

}
