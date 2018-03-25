package com.inyl.study.bootapi.example.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
public class UserRepositoryTest {
    @Autowired
    private UserRepository testRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void init() {
        User user = new User();
        user.setName("admin");

        User user2 = new User();
        user2.setName("manager");

        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(user2);
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setName("tester");
        testEntityManager.persistAndFlush(user);

        Optional<User> findUser = testRepository.findById(user.getId());

        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.orElseGet(null).getName()).isEqualTo(user.getName());
    }

    @Test
    public void testFindByName() {
        String findName = "manager";
        List<User> users = testRepository.findByName(findName);

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getName()).isEqualTo(findName);
    }
}