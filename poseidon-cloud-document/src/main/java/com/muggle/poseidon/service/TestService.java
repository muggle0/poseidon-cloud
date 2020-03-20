package com.muggle.poseidon.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.muggle.poseidon.entity.Test;
import com.muggle.poseidon.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-18 21:33
 */

@Service
public class TestService {
    @Autowired
    TestMapper mapper;

    @Transactional
    @LcnTransaction
    public void set(){
        Test test = new Test();
        test.setMessage("seata");
        mapper.insert(test);
    }
}
