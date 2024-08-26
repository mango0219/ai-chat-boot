package com.mango.bootchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shihw
 * @date 2024/8/24 11:34
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String sessionId;
    private String userInput;
}
