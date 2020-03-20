package com.muggle.poseidon.config;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.muggle.poseidon.handler.PoseidonZuulBlockFallbackProvider;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @program: poseidon-cloud-zuul
 * @description:
 * @author: muggle
 * @create: 2020-03-12 19:04
 */
@Configuration
public class ZuulSentinelConfig {


    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter();
    }

    @PostConstruct
    public void doInit() {
        // 注册 FallbackProvider
        ZuulBlockFallbackManager.registerProvider(new PoseidonZuulBlockFallbackProvider());
    }

}
