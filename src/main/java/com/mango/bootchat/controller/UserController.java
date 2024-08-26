package com.mango.bootchat.controller;

import com.mango.bootchat.entity.SysUser;
import com.mango.bootchat.service.SysUserService;
import com.mango.bootchat.vo.LoginUserVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author shihw
 * @date 2024/8/22 10:01
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * @author shihw
     * @date 2024/8/22 10:29
     * @return {@link String}
     * @description 用户注册
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody SysUser sysUser) {
        boolean register = sysUserService.register(sysUser);
        return "注册成功";
    }

    /**
     * @author shihw
     * @date 2024/8/22 10:47
     * @return {@link LoginUserVo}
     * @description 用户登陆
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginUserVo login(@RequestBody SysUser sysUser) {
        LoginUserVo login = sysUserService.login(sysUser);
        return login;
    }

    @RequestMapping(value = "/verifyToken",method = RequestMethod.POST)
    public LoginUserVo verifyToken(@RequestBody String token) {
        return sysUserService.verifyToken(token);
    }

    @RequestMapping(value = "/logout/{id}",method = RequestMethod.GET)
    public String logout(@PathVariable("id") String id) {
        int logout = sysUserService.logout(id);
        return "退出成功";
    }

}
