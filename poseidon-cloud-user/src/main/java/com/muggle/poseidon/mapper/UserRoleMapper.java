package com.muggle.poseidon.mapper;

import com.muggle.poseidon.entity.UserRoleDO;
import com.muggle.poseidon.user.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRoleDO> findRoelByName(@Param("username") String username);
}
