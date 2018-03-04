package com.inyl.study.bootapi.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorld {
    private long id;
    private String title;
    private String message;
}
