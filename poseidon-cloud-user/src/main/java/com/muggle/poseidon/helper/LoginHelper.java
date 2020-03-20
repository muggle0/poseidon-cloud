package com.muggle.poseidon.helper;

import org.springframework.security.core.Authentication;

public interface LoginHelper {

    /**
     * 获取登陆凭证
     * @param username
     * @param credentials
     * @return
     */
    Authentication getAuth(String username, String credentials);

}
