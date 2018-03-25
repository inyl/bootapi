package com.inyl.study.bootapi.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {
    @GetMapping("/hello2")
    public Mono<HelloWorld> hello2() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("hi");
        return Mono.just(helloWorld);
    }
}
