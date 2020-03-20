package com.muggle.poseidon.mapper;

import com.muggle.common.user.entity.UserAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface UserAuthorityMapper extends BaseMapper<UserAuthority> {

    List<UserAuthority> getAuthoritysByUserId(Long id);
}
