package com.muggle.poseidon.mapper;

import com.muggle.poseidon.entity.UserRoleDO;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muggle.poseidon.user.pojo.UserRole;
import com.muggle.poseidon.user.pojo.UserSign;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo findByUsername(String username);

    UserInfo getByUserSign(@Param("bean") UserSign sign);


}
