package com.inyl.study.bootapi.example.handler;

import com.inyl.study.bootapi.board.controller.repository.BoardPost;
import com.inyl.study.bootapi.board.controller.repository.BoardPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BoardPostHandler {
    @Autowired
    private BoardPostRepository repository;

    public Mono<ServerResponse> list(ServerRequest request) {

        List<BoardPost> all = repository.findAll();

        Flux<BoardPost> boardPostFlux = Flux.fromIterable(all);
        return ServerResponse.ok().body(boardPostFlux, BoardPost.class);
    }
}
