package com.muggle.poseidon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: poseidon-cloud-zuul
 * @description:
 * @author: muggle
 * @create: 2020-03-11 15:25
 */
//@Component
public class ErrorResultFilter extends ZuulFilter {
    /** logger */
    private static final Logger log = LoggerFactory.getLogger(ErrorResultFilter.class);
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        return null;
    }
}
