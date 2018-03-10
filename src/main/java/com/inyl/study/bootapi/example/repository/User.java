package com.inyl.study.bootapi.example.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USER")
@Entity
@Getter
@Setter
public class User {
    @Column(name = "ID")
    @Id
    private long id;

    @Column(name = "NAME")
    private String name;
}
