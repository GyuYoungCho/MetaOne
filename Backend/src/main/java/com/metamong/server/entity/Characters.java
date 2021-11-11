package com.metamong.server.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Characters extends BaseEntity{
    private String name;

}
