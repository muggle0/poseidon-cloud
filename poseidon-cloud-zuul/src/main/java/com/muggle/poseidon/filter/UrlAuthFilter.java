package com.muggle.poseidon.filter;

import com.muggle.poseidon.util.JwtTokenUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: poseidon-cloud-zuul
 * @description: 网关过滤器，url过滤器
 * @author: muggle
 * @create: 2019-11-07
 **/

@Component
@RefreshScope
public class UrlAuthFilter extends ZuulFilter {

    @Value("${poseidon.root.token}")
    private String rootToken;

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(UrlAuthFilter.class);

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private AntPathMatcher pathMatcher=new AntPathMatcher();

    @Value("${poseidon.credential}")
    private String credential;
    /**
     * 前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        List<String> strings = (List<String>) redisTemplate.opsForValue().get("auth:zuul:url");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        boolean match = false;
        String requestURI = request.getRequestURI();
        boolean empty = CollectionUtils.isEmpty(strings);
        if (empty){
            return null;
        }
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (pathMatcher.match(s,requestURI)){
                match=true;
            }
        }
        // 如果是需要验证登录的url
        if (match){
            String token = request.getHeader("token");
            if (token!=null){
                if (rootToken.equals(token)) {
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseBody("{\"result\":\"username is not correct!\"}");
                }
                try {
                    // 查看token是否可解析并带有版本号
                    String random = JwtTokenUtils.getRandom(token, credential);
                    if (random!=null){
                        return null;
                    }
                }catch (Exception e){
                    log.error("token 解析异常",e);
                }
            }
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");
        }
        return null;
    }
}
