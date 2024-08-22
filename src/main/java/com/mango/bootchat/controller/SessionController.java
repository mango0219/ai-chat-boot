package com.mango.bootchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shihw
 * @date 2024/8/22 10:01
 * @description
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
