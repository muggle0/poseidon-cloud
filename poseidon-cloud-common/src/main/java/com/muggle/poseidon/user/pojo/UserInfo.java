package com.muggle.poseidon.user.pojo;

import java.time.LocalDateTime;
import java.util.Collection;

import com.muggle.poseidon.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserInfo对象", description="用户表")
public class UserInfo extends BaseBean implements UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "凭证有效")
    private boolean credentialsNonExpired;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;


    private String email;

    private boolean enabled;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "头像")
    private String imgUrl;

    @ApiModelProperty(value = "别名")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "json 用户信息")
    private String context;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private Long id;

    private Collection<? extends GrantedAuthority> authorities;


}
