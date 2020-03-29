package com.muggle.poseidon.service.impl;

import com.muggle.poseidon.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @program: poseidon-cloud-core
 * @description:
 * @author: muggle
 * @create: 2020-03-10 10:27
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final static String sourceStr="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcefghijklmnopqrstuvwxyz";

    @Override
    public String setAndGetVerificat(String type, String key) {
        if ("LOGIN".equals(type)){
            String result = this.randomVerificat();
            stringRedisTemplate.opsForValue().set(key,result);
            return result;
        }
        return null;
    }

    private String randomVerificat() {

        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=sourceStr.charAt(new Random().nextInt(sourceStr.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public String getAndDeletVerificat(String type, String key) {
        return null;
    }
}
