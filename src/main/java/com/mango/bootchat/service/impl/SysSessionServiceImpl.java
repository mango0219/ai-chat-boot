package com.mango.bootchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mango.bootchat.entity.SysSession;
import com.mango.bootchat.mapper.SysSessionMapper;
import com.mango.bootchat.service.SysSessionService;
import org.springframework.stereotype.Service;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
@Service
public class SysSessionServiceImpl extends ServiceImpl<SysSessionMapper, SysSession> implements SysSessionService {
}
