package com.mango.bootchat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shihw
 * @date 2024/8/22 10:34
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {
    private String id;
    private String username;
    private String token;
}
