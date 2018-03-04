package com.inyl.study.bootapi.example;


import com.inyl.study.bootapi.example.handler.HelloWorldHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloWorldController.class)
@Import(value = HelloWorldHandler.class)
public class HelloWorldControllerTest {
    @Autowired
    private WebTestClient webClient;


    @Test
    public void test_HelloWorldRouter() {
        HelloWorld responseBody = webClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(HelloWorld.class)
                .returnResult().getResponseBody();

        assertThat(responseBody.getMessage()).isEqualTo("hi");
    }

}