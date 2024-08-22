package com.mango.bootchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mango.bootchat.mapper")
@SpringBootApplication
public class BootChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootChatApplication.class, args);
    }

}
