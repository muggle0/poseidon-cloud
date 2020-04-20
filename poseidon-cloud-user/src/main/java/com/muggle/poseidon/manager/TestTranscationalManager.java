package com.muggle.poseidon.manager;

import com.muggle.poseidon.base.exception.SimplePoseidonException;
import com.muggle.poseidon.mapper.UserSignMapper;
import com.muggle.poseidon.user.pojo.UserSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/16
 **/

@Service
public class TestTranscationalManager {

    @Autowired
    UserSignMapper signMapper;


    public void test() {
        UserSign userSign = new UserSign();
        userSign.setAuthType("manager-test").setCredentials("spring-transactional-test");
        signMapper.insert(userSign);
        throw new SimplePoseidonException("抛出异常");
    }

    @Transactional
    public void test0() {
        UserSign userSign = new UserSign();
        userSign.setAuthType("manager-test").setCredentials("spring-transactional-test");
        signMapper.insert(userSign);
        throw new SimplePoseidonException("抛出异常");

    }
}
