package com.muggle.poseidon.config.security.filter;

import lombok.Data;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/29$
 **/

@Data
public class LoginMeesageDO {
    private String username;
    private String password;
    private String loginType;
    private String verification;
}
