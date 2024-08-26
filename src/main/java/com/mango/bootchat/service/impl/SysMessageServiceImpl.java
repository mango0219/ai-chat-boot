package com.mango.bootchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mango.bootchat.ai.DataMemory;
import com.mango.bootchat.entity.SysMessage;
import com.mango.bootchat.mapper.SysMessageMapper;
import com.mango.bootchat.service.SysMessageService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author shihw
 * @date 2024/8/22 09:48
 * @description
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {

    @Resource
    private OpenAiChatModel openAiChatModel;

    @Resource
    private DataMemory dataMemory;

    @Resource
    private SysMessageMapper sysMessageMapper;

    /**
     * @author shihw
     * @date 2024/8/24 11:18
     * @return {@link Flux< ServerSentEvent< String>>}
     * @description 问答
     */
    @Override
    public Flux<ServerSentEvent<String>> sendMessage(SysMessage sysMessage) {
        String prompt = sysMessage.getTextContent();
        ChatClient client = ChatClient.create(openAiChatModel);
        String promptWithContext = """
                下面是上下文信息
                ------------
                {question_answer_context}
                ------------
                给定的上下文和提供的历史信息，而不是事先的知识，回复用户的意见，如果答案不在上下文中，告诉用户你不能回答这个问题。
                """ + prompt;
        //QuestionAnswerAdvisor questionAnswerAdvisor = new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults(), promptWithContext);
        MessageChatMemoryAdvisor dataAdvisor = new MessageChatMemoryAdvisor(dataMemory,sysMessage.getSessionId(),10);
        return client.prompt()
                .user(prompt)
                .advisors(dataAdvisor)
                .stream()
                .content()
                .map(content -> ServerSentEvent.builder(content)
                .event("message").build());
    }

    @Override
    public List<SysMessage> getMessageList(String sessionId) {
        QueryWrapper<SysMessage> queryWrapper = new QueryWrapper<SysMessage>()
                .eq("session_id", sessionId)
                .orderByAsc("create_time");
        return sysMessageMapper.selectList(queryWrapper);
    }

}
