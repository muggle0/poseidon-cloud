package com.muggle.poseidon.mapstruct;

import com.muggle.common.user.entity.UserAuthority;
import com.muggle.common.user.entity.UserInfo;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2020-03-10 14:21
 */
@Mapper(componentModel = "spring")
@Component
public interface UserInfoMap {

    @Mappings({})
    List<UserAuthority> rolemaping(List<UserAuthorityDO> userAuthority);

    @Mappings({})
    SimpleUserDO userInfoMaping(UserInfo userInfo);
}
