package com.muggle.manager.impl;


import com.muggle.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class RedisTool implements RedisManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // todo
    public void lua() {
        String key = "ss";
        String value = "ssssdasda";
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        String UNLOCK_LUA = sb.toString();
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")));
        };
        Boolean execute = stringRedisTemplate.execute(callback);
        System.out.println(execute);
    }

}
