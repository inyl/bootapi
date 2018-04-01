package com.inyl.study.bootapi.board.controller.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "board_post")
@Getter
@Setter
public class BoardPost {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "board-post-seq-gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "board-post-seq-gen", sequenceName = "board_post_id_seq")
    private long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_date", nullable = false)
    private Date createdAt;

    @Column(name = "updated_date", nullable = false)
    private Date updatedAt;

    @Column(name = "author_id", nullable = false)
    private int authorId;

    @Column(name = "visibleYn")
    private Boolean visibleYn;
}
