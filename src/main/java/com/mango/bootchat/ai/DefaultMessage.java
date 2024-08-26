package com.mango.bootchat.ai;

import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.MessageType;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/21 17:23
 * @description
 */
public class DefaultMessage extends AbstractMessage {
    protected DefaultMessage(MessageType messageType, String content) {
        super(messageType, content);
    }

    public DefaultMessage(MessageType messageType, String textContent, List<Media> media) {
        super(messageType, textContent, media);
    }
}
