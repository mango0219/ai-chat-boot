package com.mango.bootchat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shihw
 * @date 2024/8/22 09:26
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_session")
public class SysSession {
    @TableId
    private String id;
    private Date createTime;
    private Date updateTime;
    private String title;
    private String userId;

    //@TableField(exist = false)
    //private List<SysMessage> messageList;
}
