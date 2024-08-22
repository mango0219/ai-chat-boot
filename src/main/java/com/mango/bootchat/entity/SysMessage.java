package com.mango.bootchat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Date createTime;
    private Date updateTime;
    private String messageType;
    private String textContent;
    private List<Media> medias;
    private String sessionId;

    //@TableField(exist = false)
    //private SysSession session;
}
