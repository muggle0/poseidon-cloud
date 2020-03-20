package com.muggle.poseidon.handler;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: poseidon-cloud-zuul
 * @description:
 * @author: muggle
 * @create: 2020-03-13 09:34
 */
public class PoseidonZuulBlockFallbackProvider implements ZuulBlockFallbackProvider {
    /** logger */
    private static final Logger log = LoggerFactory.getLogger(PoseidonZuulBlockFallbackProvider.class);
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable cause) {

        if (cause instanceof BlockException) {
            return new BlockResponse(429, "请求未响应请稍后再试", route);
        } else {
            log.error("sentinel PoseidonZuulBlockFallbackProvider:",cause);
            return new BlockResponse(500, "未知错误。。。。", route);
        }
    }
}
