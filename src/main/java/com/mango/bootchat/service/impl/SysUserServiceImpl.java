package com.mango.bootchat.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mango.bootchat.entity.SysUser;
import com.mango.bootchat.mapper.SysUserMapper;
import com.mango.bootchat.service.SysUserService;
import com.mango.bootchat.system.exception.ServiceException;
import com.mango.bootchat.system.exception.UsernameExistException;
import com.mango.bootchat.system.exception.UsernameOrPasswordErrorException;
import com.mango.bootchat.system.exception.UsernamePasswordEmptyException;
import com.mango.bootchat.utils.Base64Utils;
import com.mango.bootchat.utils.MD5Utils;
import com.mango.bootchat.vo.LoginUserVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String REDIS_PRE = "login:user:";

    /**
     * @author shihw
     * @date 2024/8/22 10:20
     * @return {@link boolean}
     * @description 用户注册
     */
    @Override
    public boolean register(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", sysUser.getUsername());
        List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
        if (!sysUsers.isEmpty()){
            // 抛出异常
            log.error("[register 用户名已经存在] username:{}", sysUser.getUsername());
            throw new UsernameExistException("用户名已经存在");
        }
        String id = IdUtil.simpleUUID();
        log.info("[id] length: {}", id.length());
        sysUser.setId(id);
        sysUser.setPassword(MD5Utils.inputPassToFormPass(sysUser.getPassword()));
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        int insert = sysUserMapper.insert(sysUser);
        if (insert>0){
            return true;
        }
        log.error("[register 未知异常] username:{}", sysUser.getUsername());
        throw new ServiceException("未知异常");
    }

    /**
     * @author shihw
     * @date 2024/8/22 10:47
     * @return {@link boolean}
     * @description 用户登陆
     */
    @Override
    public LoginUserVo login(SysUser sysUser) {
        String password = sysUser.getPassword();
        String username = sysUser.getUsername();
        log.info("[login] username:{},password:{}", username, password);
        if (StringUtils.isBlank(password) || StringUtils.isBlank(username)){
            log.error("[login 用户名或密码为空] username:{},password:{}", username, password);
            throw new UsernamePasswordEmptyException("用户名或密码为空");
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", MD5Utils.inputPassToFormPass(password));
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        if (user == null){
            log.error("[login 用户名或密码错误] username:{},password:{}", username, password);
            throw new UsernameOrPasswordErrorException("用户名或密码错误");
        }
        // 登陆成功
        String encode = Base64Utils.encode(user.getId());
        redisTemplate.opsForValue().set(REDIS_PRE+encode,"ok",1l, TimeUnit.HOURS);

        LoginUserVo loginUserVo = new LoginUserVo(user.getId(), user.getUsername(),encode);
        return loginUserVo;
    }
}
