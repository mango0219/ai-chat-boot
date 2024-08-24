package com.mango.bootchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mango.bootchat.bo.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 09:25
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_message")
public class SysMessage {
    @TableId
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String messageType;
    private String textContent;
    private List<Media> medias;
    private String sessionId;

    @TableField(exist = false)
    private SysSession session;
}
