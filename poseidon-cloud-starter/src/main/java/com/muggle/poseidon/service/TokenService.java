package com.muggle.poseidon.service;

import com.muggle.poseidon.entity.AuthUrlPathDO;
import com.muggle.poseidon.entity.UserRoleDO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface TokenService extends UserDetailsService {

    UserDetails getUserById(Long id);

    /**
    * @author muggle
    * @Description: 权限匹配接口
    * @Param:  path为请求的 url  roleCodes 权限编码集合
    * @return:
    * @date 2019/11/5 18:05
    */
    boolean rooleMatch(List<UserRoleDO> roleDOS , String path);

    /**
     * 项目初始化插入权限表
     * @param list
     */
    void saveUrlInfo(List<AuthUrlPathDO> list);


    List<UserRoleDO> getUserRole(String username);

}
