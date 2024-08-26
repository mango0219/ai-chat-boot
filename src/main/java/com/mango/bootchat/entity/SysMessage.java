package com.mango.bootchat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mango.bootchat.ai.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.MessageType;

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
    private MessageType messageType;
    private String textContent;
    private List<Media> medias;
    private String sessionId;

    @TableField(exist = false)
    private SysSession session;

    public SysMessage(String id, Date createTime, Date updateTime, MessageType messageType, String textContent, List<Media> medias, String sessionId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.messageType = messageType;
        this.textContent = textContent;
        this.medias = medias;
        this.sessionId = sessionId;
    }
}
