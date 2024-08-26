package com.mango.bootchat.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mango.bootchat.entity.SysMessage;
import com.mango.bootchat.entity.SysSession;
import com.mango.bootchat.mapper.SysMessageMapper;
import com.mango.bootchat.mapper.SysSessionMapper;
import com.mango.bootchat.service.SysSessionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysSessionServiceImpl extends ServiceImpl<SysSessionMapper, SysSession> implements SysSessionService {

    @Resource
    private SysSessionMapper sysSessionMapper;

    @Resource
    private SysMessageMapper sysMessageMapper;

    /**
     * @author shihw
     * @date 2024/8/23 20:29
     * @return {@link List< SysSession>}
     * @description 获取会话列表
     */
    @Override
    public List<SysSession> getList( String userId) {
        QueryWrapper<SysSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("create_time");
        List<SysSession> sysSessions = sysSessionMapper.selectList(queryWrapper);
        return sysSessions;
    }

    /**
     * @author shihw
     * @date 2024/8/23 20:29
     * @return {@link boolean}
     * @description 新建会话
     */
    @Override
    public String createSession(String userId) {
        String id = IdUtil.simpleUUID();
        SysSession session = new SysSession(id, new Date(), new Date(), "新建会话-"+IdUtil.simpleUUID().substring(0,7), userId);
        int insert = sysSessionMapper.insert(session);
        if (insert<=0){
            throw new RuntimeException("业务异常");
        }
        return id;
    }

    /**
     * @author shihw
     * @date 2024/8/23 22:13
     * @return {@link Integer}
     * @description 删除会话
     */
    @Override
    public Integer deleteSession(String sessionId) {
        int sessionId1 = sysMessageMapper.delete(new QueryWrapper<SysMessage>().eq("session_id", sessionId));
        int i = sysSessionMapper.deleteById(sessionId);
        return 1;
    }
}
