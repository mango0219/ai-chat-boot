package com.mango.bootchat.controller;

import com.mango.bootchat.entity.SysMessage;
import com.mango.bootchat.service.SysMessageService;
import com.mango.bootchat.system.annotation.NotResponseAdvice;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 10:01
 * @description
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private SysMessageService sysMessageService;

    @NotResponseAdvice
    @PostMapping(value = "/sendMessage",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> sendMessage(@RequestBody SysMessage sysMessage) {
         return sysMessageService.sendMessage(sysMessage);
    }

    @RequestMapping(value = "/getList/{sessionId}",method = RequestMethod.GET)
    public List<SysMessage> getMessageList(@PathVariable("sessionId") String sessionId) {
        return sysMessageService.getMessageList(sessionId);
    }
}
