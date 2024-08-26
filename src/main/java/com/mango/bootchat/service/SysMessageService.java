package com.mango.bootchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mango.bootchat.entity.SysMessage;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
public interface SysMessageService extends IService<SysMessage> {
    public Flux<ServerSentEvent<String>> sendMessage(SysMessage sysMessage);

    public List<SysMessage> getMessageList(String sessionId);
}
