package com.mango.bootchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mango.bootchat.entity.SysSession;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 09:47
 * @description
 */
public interface SysSessionService extends IService<SysSession> {

    List<SysSession> getList(String userId);

    String createSession(String userId);

    Integer deleteSession(String sessionId);
}
