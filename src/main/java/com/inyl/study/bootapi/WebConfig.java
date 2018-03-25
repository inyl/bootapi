package com.inyl.study.bootapi;

import com.inyl.study.bootapi.example.handler.HelloWorldHandler;
import com.inyl.study.bootapi.example.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> routes(HelloWorldHandler handler) {
        return route(GET("/hello"), handler::helloWorld);
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return
                route(GET("/user"), handler::getUserAll)
                        .andRoute(PUT("/user"), handler::create)
                        .andRoute(GET("/user/{name}"), handler::getUser);
    }
}
