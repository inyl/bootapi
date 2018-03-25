package com.inyl.study.bootapi.example;


import com.inyl.study.bootapi.example.controller.HelloWorld;
import com.inyl.study.bootapi.example.handler.HelloWorldHandler;
import com.inyl.study.bootapi.example.handler.UserHandler;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(value = {HelloWorldHandler.class, UserHandler.class})
public class HelloWorldRouterTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserService userService;

    @Test
    public void test_HelloWorldRouter() {
        HelloWorld responseBody = webClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(HelloWorld.class)
                .returnResult().getResponseBody();

        assertThat(responseBody.getId()).isEqualTo(1);
        assertThat(responseBody.getTitle()).isEqualTo("hello");
        assertThat(responseBody.getMessage()).isEqualTo("hi");
    }

    @Test
    public void test_FailExecutePostMethod() {
        webClient.post().uri("/hello").exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}