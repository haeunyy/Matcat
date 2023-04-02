package com.greedy.matcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MatcatApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatcatApplication.class, args);
    }
}
