package com.mango.bootchat.system.exception;

/**
 * @author shihw
 * @date 2024/8/22 10:53
 * @description 用户名或者密码错误
 */
public class UsernameOrPasswordErrorException extends RuntimeException {
    public UsernameOrPasswordErrorException(String message) {
        super(message);
    }
}
