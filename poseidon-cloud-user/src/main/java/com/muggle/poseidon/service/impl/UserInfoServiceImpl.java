package com.muggle.poseidon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.user.pojo.UserSign;
import com.muggle.poseidon.base.exception.SimplePoseidonException;
import com.muggle.poseidon.feign.DocumentSeataFeign;
import com.muggle.poseidon.mapper.UserAuthorityMapper;
import com.muggle.poseidon.mapper.UserInfoMapper;
import com.muggle.poseidon.mapper.UserSignMapper;
import com.muggle.poseidon.service.IUserInfoService;
import com.muggle.poseidon.tool.SimpleIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    SimpleIdGenerator idGenerator;

    @Autowired
    UserAuthorityMapper authorityMapper;

    @Autowired
    UserSignMapper signMapper;

    @Autowired
    DocumentSeataFeign seataFeign;
    /**
     * 用户注册
     * @param userDO
     */
    @Override
    public void registration(String userDO) {
//        UserInfo userInfo = new UserInfo();
//
//        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
//        String msg = format.format(new Date());
//        idGenerator.getNextSerialNumber("PD"+msg,"usercode",6);
//        userInfo.setPassword(encoder.encode(userDO.getPassword()))
//                .setAccountNonExpired(true)
//                .setAccountNonLocked(true)
//                .setBirthday(userDO.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
//                .setCode("");
//        UserSign userSign = new UserSign();
        // todo usersign user role
    }

    @Override
    @LcnTransaction
    @Transactional
    public void test1() {
        UserSign userSign = new UserSign();
        userSign.setAuthType("seata").setCredentials("seata");
        signMapper.insert(userSign);
        seataFeign.findAll();
        throw new SimplePoseidonException("测试");
    }

}

