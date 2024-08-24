package com.mango.bootchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.mango.bootchat.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class BootChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootChatApplication.class, args);
    }

}
