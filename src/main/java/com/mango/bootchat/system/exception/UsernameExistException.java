package com.mango.bootchat.system.exception;

/**
 * @author shihw
 * @date 2024/8/22 10:25
 * @description 用户名已经存在
 */
public class UsernameExistException extends RuntimeException{
    public UsernameExistException(String message) {
        super(message);
    }
}
