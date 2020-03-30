package com.muggle.poseidon.config;

import com.muggle.poseidon.auto.PoseidonSecurityProperties;
import com.muggle.poseidon.service.TokenService;
import com.muggle.poseidon.store.SecurityStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/30$
 **/
@Configuration
public class CommonConfig {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    PoseidonSecurityProperties properties;

    @Value("${poseidon.root.token}")
    private String root;
    @Value("${spring.application.name}")
    private String application;

    @Bean
    public TokenService getTokenService(){
        return new CommonTokenService();
    }

    @Bean
    public SecurityStore securityStore(){
        return new CommonTokenStore(redisTemplate,properties,root,application);
    }
}
