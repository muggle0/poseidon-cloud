package com.muggle.poseidon.mapper;

import com.muggle.poseidon.user.pojo.SystemMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Repository
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    List<SystemMenu> findAllByUser(@Param("systemMenu") SystemMenu systemMenu,  @Param("id") Long id);
}
