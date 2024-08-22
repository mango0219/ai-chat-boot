package com.mango.bootchat.system.exception;

/**
 * @author shihw
 * @date 2024/8/22 10:28
 * @description
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
