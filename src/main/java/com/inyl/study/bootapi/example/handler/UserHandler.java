package com.inyl.study.bootapi.example.handler;

import com.inyl.study.bootapi.example.repository.User;
import com.inyl.study.bootapi.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired
    private UserService userService;

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok()
                .body(userMono.doOnNext(user -> userService.create(user)), User.class);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().build(userService.delete(Long.parseLong(id)));
    }

    public Mono<ServerResponse> getUserAll(ServerRequest request) {
        return ServerResponse.ok().body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return ServerResponse.ok().body(userService.findUserByName(request.pathVariable("name")), User.class);
    }
}
