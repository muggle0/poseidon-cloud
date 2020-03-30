package com.muggle.poseidon.config;

import com.muggle.poseidon.entity.AuthUrlPathDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
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
    public boolean rooleMatch(Set<String> rooleCodes, String path) {

        Iterator<String> iterator = rooleCodes.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            boolean match = matcher.match(next, path);
            return match;
        }
        return false;
    }

    @Override
    public void saveUrlInfo(List<AuthUrlPathDO> list) {

    }

    @Override
    public List<UserAuthorityDO> getUserAuthority(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
