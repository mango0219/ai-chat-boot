package com.mango.bootchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mango.bootchat.entity.SysUser;
import com.mango.bootchat.vo.LoginUserVo;

/**
 * @author shihw
 * @date 2024/8/22 09:47
 * @description
 */
public interface SysUserService extends IService<SysUser> {
    boolean register(SysUser sysUser);

    LoginUserVo login(SysUser sysUser);
    LoginUserVo verifyToken(String token);
    int logout(String id);
}
