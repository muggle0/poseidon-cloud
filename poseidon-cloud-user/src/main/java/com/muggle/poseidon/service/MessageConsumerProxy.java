package com.muggle.poseidon.service;

import com.muggle.poseidon.base.exception.BasePoseidonCheckException;

import java.util.Map;

public interface MessageConsumerProxy {

    void sendMessage(Map<String,String> message)throws BasePoseidonCheckException;
}
