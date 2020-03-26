package com.muggle.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/24$
 **/
//@Component
public class TokenInstanceExchangeFilterFunction implements InstanceExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> exchange(Instance instance, ClientRequest clientRequest, ExchangeFunction exchangeFunction) {

        clientRequest.headers().add("token","token");
        return  exchangeFunction.exchange(clientRequest).doOnSubscribe(s->{

        });
    }
}
