package com.mango.bootchat.system.config;

import com.mango.bootchat.system.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author shihw
 * @date 2024/8/22 13:17
 * @description MVC配置类
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * @author shihw
     * @date 2024/8/22 13:33
     * @return {@link }
     * @description 配置跨域
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedMethods("*");
    }

    /**
     * @author shihw
     * @date 2024/8/22 13:34
     * @return {@link }
     * @description 配置拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(redisTemplate))
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register","/user/login","/user/verifyToken");
    }
}
