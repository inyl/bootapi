package com.inyl.study.bootapi.board.controller.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class BoardPostRepositoryTest {
    @Autowired
    private BoardPostRepository repository;

    @Test
    public void testFindAll() {
        Iterable<BoardPost> posts = repository.findAll();
        assertThat(posts).isNotEmpty();
    }
}