package com.mango.bootchat.ai;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mango.bootchat.entity.SysMessage;
import com.mango.bootchat.entity.SysSession;
import com.mango.bootchat.mapper.SysMessageMapper;
import com.mango.bootchat.mapper.SysSessionMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author shihw
 * @date 2024/8/26 15:44
 * @description
 */
@Repository
public class DataMemory implements ChatMemory {

    @Resource
    private SysMessageMapper sysMessageMapper;

    @Resource
    private SysSessionMapper sysSessionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(String conversationId, List<Message> messages) {
        for (Message message : messages) {

            List<SysMessage> sysMessages = sysMessageMapper.selectList(new QueryWrapper<SysMessage>().eq("session_id", conversationId));
            if (sysMessages.isEmpty()){
                SysSession session = sysSessionMapper.selectById(conversationId);
                String content = message.getContent();
                if (content.length()>16){
                    session.setTitle(content.substring(0,16)+"...");
                }else {
                    session.setTitle(content);
                }
                QueryWrapper<SysSession> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", conversationId);
                sysSessionMapper.update(session, queryWrapper);
            }

            Collection<org.springframework.ai.chat.messages.Media> media = message.getMedia();
            //List<Media> mediaList = media.stream().map(this::toOurMedia).toList();
            List<Media> mediaList = null;
            SysMessage sysMessage = new SysMessage(IdUtil.simpleUUID(),new Date(),new Date(),message.getMessageType(),message.getContent(),mediaList,conversationId);
            sysMessageMapper.insert(sysMessage);
        }
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        QueryWrapper<SysMessage> queryWrapper = new QueryWrapper<SysMessage>()
                .eq("session_id", conversationId)
                .orderByDesc("create_time")
                .last("limit " + lastN);
        List<SysMessage> sysMessages = sysMessageMapper.selectList(queryWrapper);
        return sysMessages.stream().map(this::toMessage).toList();
    }

    @Override
    public void clear(String conversationId) {
        sysMessageMapper.delete(new QueryWrapper<SysMessage>().eq("session_id", conversationId));
    }


    public Message toMessage(SysMessage sysMessage){
        //List<org.springframework.ai.chat.messages.Media> list = sysMessage.getMedias().stream().map(this::toMedia).toList();
        List<org.springframework.ai.chat.messages.Media> media = new ArrayList<>();
        return new DefaultMessage(sysMessage.getMessageType(),sysMessage.getTextContent(),media);
    }

    @SneakyThrows
    public org.springframework.ai.chat.messages.Media toMedia(Media media) {
        return new org.springframework.ai.chat.messages.Media(MimeType.valueOf(media.getType()), new URL(media.getData()));
    }

    public Media toOurMedia(org.springframework.ai.chat.messages.Media media) {
        return new Media(media.getMimeType().getType(), String.valueOf(media.getData()));
    }
}
