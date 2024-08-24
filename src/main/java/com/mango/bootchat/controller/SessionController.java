package com.mango.bootchat.controller;

import com.mango.bootchat.entity.SysSession;
import com.mango.bootchat.service.SysSessionService;
import com.mango.bootchat.system.annotation.SuccessMessage;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 10:01
 * @description
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);
    @Resource
    private SysSessionService sysSessionService;

    /**
     * @author shihw
     * @date 2024/8/23 20:25
     * @return {@link List< SysSession>}
     * @description 获取会话列表
     */
    @RequestMapping(value = "/getList/{userId}",method = RequestMethod.GET)
    public List<SysSession> getList(@PathVariable("userId") String userId){
        System.out.println();
        return sysSessionService.getList(userId);
    }

    /**
     * @author shihw
     * @date 2024/8/23 22:13
     * @return {@link String}
     * @description 创建新会话
     */
    @RequestMapping(value = "/createSession/{userId}",method = RequestMethod.GET)
    public String createSession(@PathVariable("userId") String userId){
        log.info("[createSession] userId:{}",userId);
        String sessionId = sysSessionService.createSession(userId);
        return sessionId;
    }

    /**
     * @author shihw
     * @date 2024/8/23 22:13
     * @return {@link String}
     * @description 删除会话
     */
    @SuccessMessage("删除成功")
    @RequestMapping(value = "/deleteSession/{sessionId}",method = RequestMethod.GET)
    public String deleteSession(@PathVariable("sessionId") String sessionId){
        Integer integer = sysSessionService.deleteSession(sessionId);
        return "删除成功";
    }
}
