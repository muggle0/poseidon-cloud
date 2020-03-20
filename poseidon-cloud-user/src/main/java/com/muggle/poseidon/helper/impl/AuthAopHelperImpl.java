package com.muggle.poseidon.helper.impl;

import com.muggle.poseidon.helper.RequestLockHelper;
import com.muggle.poseidon.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2020-01-10
 **/

@Profile("dev")
@Component
public class AuthAopHelperImpl implements RequestLockHelper {
    @Autowired
    TokenService tokenService;
    @Override
    public boolean accsess(HttpServletRequest request) {
        /*UserDetails userDetails = tokenService.loadUserByUsername("admin");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        token.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(token);*/
        return true;
    }
}
