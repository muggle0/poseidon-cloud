package com.muggle.poseidon.mapstruct;

import com.muggle.poseidon.user.pojo.UserAuthority;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mappings({@Mapping(target = "authorities", ignore = true)})
    SimpleUserDO userInfoMaping(UserInfo userInfo);

    @Mappings({@Mapping(target = "authorities", ignore = true)})
    UserInfo DoToInfoMaping(SimpleUserDO userDO);

    @Mappings({})
    List<UserAuthorityDO> roleDoToPoMaping(List<UserAuthority> authorities);

    @Mappings({})
    UserAuthority authToDo(UserAuthorityDO authorityDO);

    @Mappings({})
    UserAuthorityDO doToAuth(UserAuthority userAuthority);
}
