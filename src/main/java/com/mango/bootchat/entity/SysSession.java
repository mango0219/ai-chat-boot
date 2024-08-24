package com.mango.bootchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String title;
    private String userId;

    public SysSession(String id, Date createTime, Date updateTime, String title, String userId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.userId = userId;
    }

    @TableField(exist = false)
    private List<SysMessage> messageList;
}
