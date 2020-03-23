package com.muggle.poseidon.config;

import com.muggle.poseidon.aop.FeignAop;
import com.muggle.poseidon.aop.FeignInterceptor;
import feign.RequestInterceptor;
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

    @Bean
    RequestInterceptor getInsetceptor(){
        return new FeignInterceptor();
    }

    @Bean
    FeignAop getFeignAop(){
        return new FeignAop();
    }
}
