package com.highio.shoping.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {

    private static final String slat = "3f7fas9s";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String hexBySlat(String frontHex,String slat){
        return md5(frontHex+slat);
    }


}
