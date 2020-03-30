package com.muggle.poseidon.config;

import com.muggle.poseidon.aop.FeignAop;
import com.muggle.poseidon.aop.FeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-22 08:27
 */
@Configuration
public class RequestConfig {
    @Value("${poseidon.root.token}")
    private String rootToken;

    @Bean
    RequestInterceptor getInsetceptor(){
        return new FeignInterceptor(rootToken);
    }

    @Bean
    FeignAop getFeignAop(){
        return new FeignAop();
    }
}
