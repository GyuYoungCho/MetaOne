package com.metamong.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer id = null;
}