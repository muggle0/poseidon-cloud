package com.muggle.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.values.InstanceId;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/24$
 **/
@Component
@RefreshScope
public class TokenHttpHeadersProvider implements HttpHeadersProvider {

    @Value("${poseidon.root.token}")
    private String token;
    @Override
    public HttpHeaders getHeaders(Instance instance) {
        InstanceId id = instance.getId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token",token);
        return httpHeaders;
    }
}
