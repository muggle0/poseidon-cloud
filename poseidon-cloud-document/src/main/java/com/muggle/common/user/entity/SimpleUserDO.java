package com.muggle.common.user.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @program: poseidon-cloud-starter
 * @description:
 * @author: muggle
 * @create: 2019-11-05
 **/

@Data
public class SimpleUserDO implements UserDetails, Serializable {

    private static final long serialVersionUID = 1062746572258230221L;

    private String password;

    private String username;

    // 性别
    private int gender;

    // 生日
    private Date birthday;

    // 昵称
    private String nickName;

    // 验证码
    private String verifCode;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private  Collection<? extends GrantedAuthority> authorities;

    private String employeeNumber;

    private Date createedTime;

}
