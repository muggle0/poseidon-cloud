package com.muggle.poseidon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muggle.poseidon.user.pojo.UserAuthority;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.user.pojo.UserSign;
import com.muggle.poseidon.entity.AuthUrlPathDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import com.muggle.poseidon.mapper.UserAuthorityMapper;
import com.muggle.poseidon.mapper.UserInfoMapper;
import com.muggle.poseidon.service.IUserAuthorityService;
import com.muggle.poseidon.service.TokenService;
import com.muggle.poseidon.util.PoseidonIdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @program: poseidon-cloud-user
 * @description: 测试
 * @author: muggle
 * @create: 2019-12-04
 **/

@Service
public class PoseidonTokenService implements TokenService {
    @Autowired
    PoseidonIdGenerator idGenerator;
    @Autowired
    IUserAuthorityService userAuthorityService;
    UserAuthorityMapper userAuthorityMapper;
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAuthorityMapper authorityMapper;
    private AntPathMatcher matcher=new AntPathMatcher();

    @Override
    public UserDetails getUserById(Long id) {
        UserInfo userInfo = userInfoMapper.selectById(id);

        return userInfo;
    }

    @Override
    public boolean rooleMatch(Set<String> rooleCodes, String path) {
        Iterator<String> iterator = rooleCodes.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            boolean match = matcher.match(next, path);
            return match;
        }
        return false;
    }



    /**
     * 保存controller 的url
     * @param list
     */
    @Override
    public void saveUrlInfo(List<AuthUrlPathDO> list) {
        QueryWrapper<UserAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application",appName);
        userAuthorityService.remove(queryWrapper);
        Map<String, UserAuthority> authMap = new HashMap<>();
        ArrayList<UserAuthority> userAuthorities = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AuthUrlPathDO bean = list.get(i);
            if (bean.getMethodURL().contains("/error")|| bean.getMethodURL().contains("/swagger")){
                continue;
            }
            String className = bean.getClassName();
            UserAuthority userAuthority = authMap.get(className);
            UserAuthority child = new UserAuthority();
            BeanUtils.copyProperties(bean,child);
            child.setId(idGenerator.nextId());
            child.setUrl(bean.getMethodURL()+"/**");
            child.setDescription(bean.getMethodDesc());
            child.setGmtCreate(new Date());
            child.setEnable(true);
            if (userAuthority==null){
                UserAuthority parentAuth = new UserAuthority();
                BeanUtils.copyProperties(bean,parentAuth);
                parentAuth.setUrl(bean.getClassUrl()+"/**");
                parentAuth.setDescription(bean.getClassDesc());
                parentAuth.setGmtCreate(new Date());
                parentAuth.setEnable(true);
                parentAuth.setMethodName(null);
                parentAuth.setRequestType(null);
                long parentId = idGenerator.nextId();
                parentAuth.setId(parentId);
                authMap.put(parentAuth.getClassName(),parentAuth);
                child.setParentId(parentId);
            }else {
                child.setParentId(userAuthority.getId());
            }
            userAuthorities.add(child);
        }
        userAuthorities.addAll(authMap.values());
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setDescription("管理员权限").setEnable(true);
        userAuthority.setGmtCreate(new Date())
        .setUrl("/**").setApplication(appName).setId(idGenerator.nextId());
        userAuthorities.add(userAuthority);
        userAuthorityService.saveBatch(userAuthorities);
    }

    @Override
    public List<UserAuthorityDO> getUserAuthority(String username) {

        List<UserAuthorityDO> auths=userInfoMapper.findAuthByUsername(username);
        return auths;
    }


    public UserInfo getByUserSign(UserSign sign) {

        return  userInfoMapper.getByUserSign(sign);
    }

    public List<UserAuthority> getUserAuthority(UserInfo userInfo) {

        List<UserAuthority> authorities=authorityMapper.getAuthoritysByUserId(userInfo.getId());
        return authorities;
    }

    /**
     * 登陆查找用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserInfo userInfo= userInfoMapper.findByUsername(username);
       return userInfo;
    }
}
