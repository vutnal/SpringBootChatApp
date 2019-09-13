package com.example.chatty.util;

import com.example.chatty.ChattyApplication;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChatUtil {

    public static String getUniqueNodeId() {
        String hostName = "";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            hostName = DigestUtils.md5DigestAsHex(ip.getHostName().getBytes());
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        return hostName;
    }

    public static void main(String[] args){
        try {
            System.out.println(ChatUtil.getUniqueNodeId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
