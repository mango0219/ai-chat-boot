package com.mango.bootchat.system.exception;

/**
 * @author shihw
 * @date 2024/8/22 10:48
 * @description 用户名或者密码为空
 */
public class UsernamePasswordEmptyException extends RuntimeException {
    public UsernamePasswordEmptyException(String msg) {
        super(msg);
    }
}
