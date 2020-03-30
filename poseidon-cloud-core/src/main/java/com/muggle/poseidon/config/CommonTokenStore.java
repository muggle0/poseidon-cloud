package com.muggle.poseidon.config;

import com.muggle.poseidon.auto.PoseidonSecurityProperties;
import com.muggle.poseidon.base.exception.BasePoseidonCheckException;
import com.muggle.poseidon.base.exception.SimplePoseidonCheckException;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import com.muggle.poseidon.store.SecurityStore;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.util.JwtTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/24$
 **/
public class CommonTokenStore implements SecurityStore {


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    PoseidonSecurityProperties properties;

    @Value("${poseidon.root.token}")
    private String root;
    @Value("${spring.application.name}")
    private String application;


    @Override
    public UserDetails getUserdetail(String token)throws BasePoseidonCheckException {
        String credential = properties.getCredential();
        if (token.equals(root)){
            String random = JwtTokenUtils.getRandom(token, credential);
            UserAuthorityDO userAuthorityDO = new UserAuthorityDO();
            userAuthorityDO.setEnable(true);
            userAuthorityDO.setUrl("/**");
            userAuthorityDO.setApplication(application);
            UserInfo userInfo = new UserInfo().setUsername("root").setAuthorities(Arrays.asList(userAuthorityDO)).setAccountNonExpired(true)
                    .setAccountNonLocked(true).setNickname("root").setEnabled(true);
            userInfo.setCode(random);
            return userInfo;
        }
        String storeKey = JwtTokenUtils.getStoreKey(token, credential);
        SimpleUserDO userDO = (SimpleUserDO) redisTemplate.opsForValue().get(storeKey);
        String version = JwtTokenUtils.getRandom(token, credential);
        for (String random : userDO.getRandoms()) {
            if (random.equals(version)){
                UserInfo userInfo =new UserInfo();
                BeanUtils.copyProperties(userDO,userInfo);
                return userInfo;
            }
        }
        return null;
    }

    @Override
    public String saveUser(UserDetails userDetails, long expirationTime, String key) {
       return null;
    }

    @Override
    public Boolean cleanToken(String token) {
//        String key = JwtTokenUtils.getKey(token, properties.getCredential());
//        Boolean delete = redisTemplate.delete(key);
//        return delete;
        return false;
    }

    @Override
    public void setExpirationTime(long expirationTime, String token) {
//        String key = JwtTokenUtils.getKey(token, properties.getCredential());
//        redisTemplate.expire(key, expirationTime, TimeUnit.HOURS);
        return;
    }

    @Override
    public UserDetails processToken(UserDetails userDetails, String token, Function<UserDetails, UserDetails> function) {
        return null;
    }

}
