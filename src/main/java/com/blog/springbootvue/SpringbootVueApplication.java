package com.blog.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blog.springbootvue.mapper")
public class SpringbootVueApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootVueApplication.class, args);
    }
}
