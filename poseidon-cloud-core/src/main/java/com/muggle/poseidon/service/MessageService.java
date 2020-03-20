package com.muggle.poseidon.service;

import org.springframework.stereotype.Service;

/**
 * @program: poseidon-cloud-core
 * @description:
 * @author: muggle
 * @create: 2020-03-10 10:26
 */


public interface MessageService {

    String setAndGetVerificat(String type,String key);

    String getAndDeletVerificat(String type,String key);
}
