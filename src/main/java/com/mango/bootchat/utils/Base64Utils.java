package com.mango.bootchat.utils;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * @author shihw
 * @date 2024/8/22 11:05
 * @description Base64加密/解密
 */
public class Base64Utils {
    private static final String pre_salt = "0s2h1w9";;
    private static final String post_salt = "p0q3w01";;

    /**
     * @author shihw
     * @date 2024/8/22 11:14
     * @return {@link String}
     * @description 加密
     */
    public static String encode(String data){
        data = pre_salt + data + post_salt;
        return Base64.encodeBase64String(data.getBytes(StandardCharsets.UTF_8));
    }

    
    /**
     * @author shihw
     * @date 2024/8/23 14:57
     * @return {@link String}
     * @description 解密
     */
    public static String decode(String data) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null");
        }
        try {
            byte[] bytes = Base64.decodeBase64(data);
            String decoded = new String(bytes, StandardCharsets.UTF_8);
            if (!decoded.startsWith(pre_salt) || !decoded.endsWith(post_salt)) {
                throw new IllegalArgumentException("Invalid data: salts do not match");
            }
            return decoded.substring(pre_salt.length(), decoded.length() - post_salt.length());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 input", e);
        }
    }
}
