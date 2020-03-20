package com.muggle.poseidon.config;

import com.muggle.poseidon.entity.AuthUrlPathDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import com.muggle.poseidon.service.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-17 12:52
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public UserDetails getUserById(Long aLong) {
        return null;
    }

    @Override
    public boolean rooleMatch(Set<String> set, String s) {
        return false;
    }

    @Override
    public void saveUrlInfo(List<AuthUrlPathDO> list) {

    }

    @Override
    public List<UserAuthorityDO> getUserAuthority(String s) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
