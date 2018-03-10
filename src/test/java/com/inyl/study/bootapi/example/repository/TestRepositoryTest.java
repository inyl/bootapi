package com.inyl.study.bootapi.example.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepositoryTest {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setName("admin");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("manager");
        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(user2);
    }

    @Test
    public void testFindById() {
        Optional<User> user = testRepository.findById(1L);

        assertThat(user.isPresent()).isTrue();
        assertThat(user.orElseGet(null).getName()).isEqualTo("admin");
    }

    @Test
    public void testFindByName() {
        String findName = "manager";
        List<User> users = testRepository.findByName(findName);

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getId()).isEqualTo(2L);
        assertThat(users.get(0).getName()).isEqualTo(findName);
    }
}