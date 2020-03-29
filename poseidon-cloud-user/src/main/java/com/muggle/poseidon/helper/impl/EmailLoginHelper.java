package com.muggle.poseidon.helper.impl;

import com.muggle.poseidon.user.pojo.UserSign;
import com.muggle.poseidon.helper.LoginHelper;
import com.muggle.poseidon.config.security.properties.VerlifaTypeEnum;
import com.muggle.poseidon.service.MessageService;
import com.muggle.poseidon.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2019-12-02
 **/

@Service("emailLoginHelper")
public class EmailLoginHelper implements LoginHelper {
    @Autowired
    TokenService tokenService;
    @Autowired
    MessageService messageService;

    @Override
    public Authentication getAuth(String username, String credentials) {

        return null;
    }
}
