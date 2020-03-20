package com.muggle.poseidon.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.muggle.poseidon.entity.DocTrasctionTest;
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

    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public void set(){
        DocTrasctionTest docTrasctionTest = new DocTrasctionTest();
        docTrasctionTest.setMessage("tx-Lcn");
        docTrasctionTest.setNumber(222);
        mapper.insert(docTrasctionTest);
    }
}
