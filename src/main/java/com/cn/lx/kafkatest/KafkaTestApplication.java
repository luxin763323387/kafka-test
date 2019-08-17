package com.cn.lx.kafkatest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.cn.lx.kafkatest.dao")
@SpringBootApplication
public class KafkaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestApplication.class, args);
    }

}
