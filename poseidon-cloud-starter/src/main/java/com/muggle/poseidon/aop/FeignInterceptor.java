package com.muggle.poseidon.aop;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-22 08:08
 */
public class FeignInterceptor implements RequestInterceptor {

    private String root;

    public FeignInterceptor(String root) {
        this.root = root;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        if ("".equals(token)||token==null){
            requestTemplate.header("token",root);
        }else {
            requestTemplate.header("token",request.getHeader("token"));
        }
    }
}
