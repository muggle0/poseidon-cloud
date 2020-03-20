package com.muggle.poseidon.service.impl;

import com.muggle.poseidon.user.pojo.UserAuthority;
import com.muggle.poseidon.mapper.UserAuthorityMapper;
import com.muggle.poseidon.service.IUserAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Service
public class UserAuthorityServiceImpl extends ServiceImpl<UserAuthorityMapper, UserAuthority> implements IUserAuthorityService {

    public void test(){
        UserAuthorityMapper baseMapper = getBaseMapper();
    }
}
