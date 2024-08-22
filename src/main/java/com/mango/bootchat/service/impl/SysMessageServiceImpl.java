package com.mango.bootchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mango.bootchat.entity.SysMessage;
import com.mango.bootchat.mapper.SysMessageMapper;
import com.mango.bootchat.service.SysMessageService;
import org.springframework.stereotype.Service;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {
}
