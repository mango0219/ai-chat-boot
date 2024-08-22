package com.mango.bootchat.system.aop;

import com.alibaba.fastjson2.JSONObject;
import com.mango.bootchat.system.annotation.NotResponseAdvice;
import com.mango.bootchat.system.result.Result;
import com.mango.bootchat.system.result.ResultUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author shihw
 * @date 2024/8/22 10:03
 * @description
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        boolean assignableFrom = returnType.getParameterType().isAssignableFrom(Result.class);
        boolean annotation = returnType.hasMethodAnnotation(NotResponseAdvice.class);
        return !assignableFrom && !annotation;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result){
            return body;
        }
        String message = "success";
        /* 处理String类型返回值 */
        if (String.class.equals(returnType.getGenericParameterType())) {
            return JSONObject.toJSONString(ResultUtil.success(message, body));
        }
        return ResultUtil.success("success",body);
    }
}
