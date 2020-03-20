package com.muggle.poseidon.service;

import com.muggle.common.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
public interface IUserInfoService extends IService<UserInfo> {

    void registration(String userDO);

    void test1();
}
