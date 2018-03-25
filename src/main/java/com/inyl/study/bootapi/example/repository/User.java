package com.inyl.study.bootapi.example.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "test_user")
@Entity
@Getter
@Setter
public class User {
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    @GeneratedValue(generator = "seq-gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq-gen", sequenceName = "test_user_id_seq")
    private long id;

    @Column(name = "name", length = 50)
    private String name;


}
