package com.inyl.study.bootapi.example;

import com.inyl.study.bootapi.example.handler.HelloWorldHandler;
import com.inyl.study.bootapi.example.handler.UserHandler;
import com.inyl.study.bootapi.example.repository.User;
import com.inyl.study.bootapi.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(value = {UserHandler.class, HelloWorldHandler.class})
public class UserRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void testUserList() {
        given(userService.findAll()).willReturn(Flux.just(new User()));

        webTestClient.get().uri("/user")
                .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                .expectBodyList(User.class);
    }

    @Test
    public void testCreate() {
        webTestClient.put().uri("/user")
                .syncBody(new User()).exchange().expectStatus().isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testFindByName() {
        given(userService.findUserByName(anyString())).willReturn(Flux.just(new User()));

        webTestClient.get().uri("/user/test")
                .exchange().expectStatus().isEqualTo(HttpStatus.OK);
    }

}
