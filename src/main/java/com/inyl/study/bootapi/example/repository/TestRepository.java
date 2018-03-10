package com.inyl.study.bootapi.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}
