package com.metamong.server.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room extends BaseEntity {

    private String name;

    private int maxPopulation;

    @OneToMany(mappedBy = "room")
    @Builder.Default
    private List<User> users = new ArrayList<>();
}
