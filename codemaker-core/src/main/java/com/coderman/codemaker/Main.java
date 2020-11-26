package com.coderman.codemaker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * description: Main <br>
 * date: 2020/7/6 23:07 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@SpringBootApplication
@MapperScan(value = "com.coderman.codemaker.dao")
@EnableConfigurationProperties
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
