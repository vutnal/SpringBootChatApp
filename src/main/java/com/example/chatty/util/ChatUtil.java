package com.example.chatty.util;

import com.example.chatty.ChattyApplication;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class ChatUtil {
    private static UUID nodeId = UUID.randomUUID();

    public static String getUniqueNodeId() {
        return nodeId.toString();
    }

    public static void main(String[] args){
        try {
            System.out.println(ChatUtil.getUniqueNodeId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
