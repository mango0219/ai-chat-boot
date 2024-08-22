package com.mango.bootchat.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author shihw
 * @date 2024/8/22 10:35
 * @description
 */
public class MD5Utils {

    private static final String salt = "0s2h1w9p0q3w01";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
}
