package com.muggle.poseidon.util;

import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.base.exception.SimplePoseidonException;
import com.muggle.poseidon.properties.SecurityMessageProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @program: poseidon-cloud-user
 * @description: 获取用户信息
 * @author: muggle
 * @create: 2020-01-10
 **/

public class UserUtils {

    public static UserInfo getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (authentication==null||"anonymousUser".equals(principal)|| SecurityMessageProperties.BAD_TOKEN.equals(principal)){
            throw new SimplePoseidonException("用户未登陆");
        }
        return (UserInfo) authentication.getDetails();
    }

}
