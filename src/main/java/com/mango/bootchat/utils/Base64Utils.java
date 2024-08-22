package com.mango.bootchat.utils;


import org.apache.commons.codec.binary.Base64;

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
        return Base64.encodeBase64String(data.getBytes());
    }

    public static String decode(String data){
        byte[] bytes = Base64.decodeBase64(data);
        String decode = new String(bytes);
        return decode.substring(pre_salt.length(), decode.length() - post_salt.length());
    }
}
