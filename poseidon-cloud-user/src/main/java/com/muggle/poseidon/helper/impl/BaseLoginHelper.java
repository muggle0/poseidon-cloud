package com.muggle.poseidon.helper.impl;

import com.muggle.common.user.entity.UserAuthority;
import com.muggle.common.user.entity.UserInfo;
import com.muggle.poseidon.entity.UserAuthorityDO;
import com.muggle.poseidon.helper.LoginHelper;
import com.muggle.poseidon.mapstruct.UserInfoMap;
import com.muggle.poseidon.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2019-12-02
 **/

@Service("baseLoginHelper")
public class BaseLoginHelper implements LoginHelper {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserInfoMap userInfoMap;
    @Override
    public Authentication getAuth(String username, String credentials) {

        UserInfo userInfo =(UserInfo) tokenService.loadUserByUsername(username);
        if (userInfo==null){
            throw new BadCredentialsException("用户名不存在");
        }
        String password = userInfo.getPassword();
        boolean matches = passwordEncoder.matches(credentials, password);
        if (!matches){
            throw new BadCredentialsException("密码错误");
        }
        List<UserAuthorityDO> authorities = tokenService.getUserAuthority(username);
        userInfo.setAuthorities(authorities);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword(),authorities);
        authenticationToken.setDetails(userInfo);
        return authenticationToken;
    }

}








