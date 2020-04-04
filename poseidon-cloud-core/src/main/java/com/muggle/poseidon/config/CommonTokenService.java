package com.muggle.poseidon.config;

import com.muggle.poseidon.entity.AuthUrlPathDO;
import com.muggle.poseidon.entity.UserRoleDO;
import com.muggle.poseidon.service.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.AntPathMatcher;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/24$
 **/
public class CommonTokenService implements TokenService {

    private AntPathMatcher matcher=new AntPathMatcher();

    @Override
    public UserDetails getUserById(Long id) {
        return null;
    }

    @Override
    public boolean rooleMatch(List<UserRoleDO> roleDOS , String path) {
        return true;
    }

    @Override
    public void saveUrlInfo(List<AuthUrlPathDO> list) {

    }

    @Override
    public List<UserRoleDO> getUserRole(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
