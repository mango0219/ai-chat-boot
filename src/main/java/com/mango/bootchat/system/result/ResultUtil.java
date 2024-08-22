package com.mango.bootchat.system.result;

/**
 * @author shihw
 * @date 2024/8/22 09:37
 * @description
 */
public class ResultUtil {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;

    public static  <T> Result<T> success(String message) {
        return result(SUCCESS,message,null);
    }

    public static  <T> Result<T> success(T data) {
        return result(SUCCESS,null,data);
    }

    public static  <T> Result<T> success(String message, T data) {
        return result(SUCCESS,message,data);
    }

    public static  <T> Result<T> fail(String message) {
        return result(ERROR,message,null);
    }

    public static  <T> Result<T> result(Integer code,String message,T data){
        return new Result(code,message,data);
    }
}
