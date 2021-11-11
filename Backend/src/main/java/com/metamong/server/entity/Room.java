package com.metamong.server.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {

    private String name;

    private int maxPopulation;

    @OneToMany(mappedBy = "room")
    private List<User> users = new ArrayList<>();
}
