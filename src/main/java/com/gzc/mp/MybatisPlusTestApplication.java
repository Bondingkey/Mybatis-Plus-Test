package com.gzc.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gzc.mp.mapper")
public class MybatisPlusTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusTestApplication.class, args);
    }
}
