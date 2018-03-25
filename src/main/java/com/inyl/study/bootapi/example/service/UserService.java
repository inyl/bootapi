package com.inyl.study.bootapi.example.service;

import com.inyl.study.bootapi.example.repository.User;
import com.inyl.study.bootapi.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Scheduler jdbcScheduler;

    @Transactional
    public Mono<User> create(User user) {
        User save = userRepository.save(user);
        return Mono.just(save);
    }

    @Transactional
    public Mono<Void> delete(Long id) {
        userRepository.deleteById(id);
        return Mono.empty();
    }

    public Flux<User> findUserByName(String name) {
        return Flux.fromIterable(userRepository.findByName(name)).publishOn(jdbcScheduler);
    }

    public Flux<User> findAll() {
        return Flux.fromIterable(userRepository.findAll()).publishOn(jdbcScheduler);
    }
}
