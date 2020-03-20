package com.muggle.common.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.muggle.poseidon.base.BaseBean;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class PoseidonUserDetail extends BaseBean implements UserDetails {


    private Long id;

    /**
     * 用户名
     * isNullAble:1
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     * isNullAble:1
     */
    @NotNull(message = "昵称不能为空")
    private String nickname;

    @JSONField(format="yyyyMMdd HH:mm:ss")
    private Date birthday;

    /**
     * isNullAble:1
     */
    @NotNull(message = "密码不能为空")
//    @Pattern(regexp = "^.{3,16}$",message = "密码必须为6到16位（字母，数字，下划线，减号）")
    private String password;

    /**
     * 账户未过期
     * isNullAble:1,defaultVal:1
     */
    private boolean accountNonExpired;

    /**
     * 账户未上锁
     * isNullAble:1
     */
    private boolean accountNonLocked;

    /**
     * 凭证未过期
     * isNullAble:1,defaultVal:1
     */
    private boolean CredentialsNonExpired;

    /**
     * 激活
     * isNullAble:1,defaultVal:1
     */
    private boolean enabled;

    /**
     * isNullAble:1
     */
    private String hashCode;

    /**
     * isNullAble:1
     */
    private String imgUrl;

    /**
     * isNullAble:1
     */
    private String phone;

    /**
     * isNullAble:1
     */
    private Integer gender;

    /**
     * isNullAble:1
     */
    @Email
    private String email;

    /**
     * 创建时间
     * isNullAble:1,defaultVal:CURRENT_TIMESTAMP
     */
    private Date creatTime;

    /**
     * isNullAble:1
     */
    private Date updateTime;

    /**
     * isNullAble:1
     */
    private Date deleteTime;

    private Set<PoseidonGrantedAuthority> authorities;

    private String uuid;

}
