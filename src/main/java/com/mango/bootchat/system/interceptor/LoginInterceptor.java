package com.mango.bootchat.system.interceptor;

import com.mango.bootchat.system.annotation.NotNeedLogin;
import com.mango.bootchat.system.exception.TokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author shihw
 * @date 2024/8/22 13:18
 * @description 登陆验证
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String REDIS_PRE = "login:user:";

    private final RedisTemplate<String,Object> redisTemplate;

    public LoginInterceptor(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不处理非方法拦截
        if (!(handler instanceof HandlerMethod methodHandle)) {
            return true;
        }
        if (methodHandle.hasMethodAnnotation(NotNeedLogin.class)){
            return true;
        }

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            throw new TokenException("Token为空，请重新登陆");
        }

        Object o = redisTemplate.opsForValue().get(REDIS_PRE + token);
        if (o == null){
            throw new TokenException("请重新登陆");
        }
        return true;
    }
}
