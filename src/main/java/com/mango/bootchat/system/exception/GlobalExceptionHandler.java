package com.mango.bootchat.system.exception;

import com.mango.bootchat.system.result.Result;
import com.mango.bootchat.system.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shihw
 * @date 2024/8/22 10:29
 * @description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @author shihw
     * @date 2024/8/22 10:32
     * @return {@link Result< String>}
     * @description 用户名已经存在异常处理
     */
    @ExceptionHandler(value = UsernameExistException.class)
    public Result<String> usernameExistException(UsernameExistException usernameExistException){
        return ResultUtil.fail(usernameExistException.getMessage());
    }

    /**
     * @author shihw
     * @date 2024/8/22 10:49
     * @return {@link Result< String>}
     * @description 用户名或密码为空
     */
    @ExceptionHandler(value = UsernamePasswordEmptyException.class)
    public Result<String> usernamePasswordEmptyException(UsernamePasswordEmptyException usernamePasswordEmptyException){
        return ResultUtil.fail(usernamePasswordEmptyException.getMessage());
    }

    /**
     * @author shihw
     * @date 2024/8/22 11:25
     * @return {@link Result< String>}
     * @description 用户名或密码错误
     */
    @ExceptionHandler(value = UsernameOrPasswordErrorException.class)
    public Result<String> usernameOrPasswordErrorException(UsernameOrPasswordErrorException usernameOrPasswordErrorException){
        return ResultUtil.fail(usernameOrPasswordErrorException.getMessage());
    }

    /**
     * @author shihw
     * @date 2024/8/22 13:28
     * @return {@link Result< String>}
     * @description Token异常
     */
    @ExceptionHandler(value = TokenException.class)
    public Result<String> tokenException(TokenException tokenException){
        return ResultUtil.fail(ResultUtil.TOKEN_NO,tokenException.getMessage());
    }

    /**
     * @author shihw
     * @date 2024/8/22 10:32
     * @return {@link Result< String>}
     * @description 异常兜底
     */
    //@ExceptionHandler(value = Exception.class)
    //public Result<String> exception(Exception exception){
    //    log.error("[Exception], meessage: {}", exception.getMessage());
    //    return ResultUtil.fail("业务异常");
    //}

}
