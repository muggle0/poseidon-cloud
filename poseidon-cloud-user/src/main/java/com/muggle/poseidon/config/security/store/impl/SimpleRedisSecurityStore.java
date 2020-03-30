package com.muggle.poseidon.config.security.store.impl;

import com.muggle.poseidon.auto.PoseidonSecurityProperties;
import com.muggle.poseidon.base.exception.BasePoseidonCheckException;
import com.muggle.poseidon.config.security.properties.VerlifaTypeEnum;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.entity.UserAuthorityDO;
import com.muggle.poseidon.mapstruct.UserInfoMap;
import com.muggle.poseidon.store.SecurityStore;
import com.muggle.poseidon.user.pojo.UserAuthority;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: poseidon-cloud-starter
 * @description: redis存储token的仓库
 * @author: muggle
 * @create: 2019-11-04
 **/
@Service
public class SimpleRedisSecurityStore implements SecurityStore {


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    PoseidonSecurityProperties properties;

    @Value("${poseidon.root.token}")
    private String root;
    @Value("${spring.application.name}")
    private String application;

    @Autowired
    UserInfoMap userInfoMap;

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
                UserInfo userInfo = userInfoMap.DoToInfoMaping(userDO);
                userInfo.setAuthorities(userDO.getAuthorities());
                return userInfo;
            }
        }
        return null;
    }

    @Override
    public String saveUser(UserDetails userDetails, long expirationTime, String key) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key",key);
        SimpleUserDO simpleUserDO = userInfoMap.userInfoMaping((UserInfo) userDetails);
        String token = JwtTokenUtils.createToken(map, properties.getCredential(), properties.getExperTime());
        String random = JwtTokenUtils.getRandom(token, properties.getCredential());
        // 从redis中拿出用户的版本号
        Object o = redisTemplate.opsForValue().get(key);
        ArrayList<String> randoms = new ArrayList<>();
        if (o!=null){
            randoms.addAll( ((SimpleUserDO) o).getRandoms());
        }
        // 将新的版本号加入到版本号list中，用于校验token
        randoms.add(random);
        simpleUserDO.setRandoms(randoms);
        simpleUserDO.setAuthorities(((List<UserAuthorityDO>) userDetails.getAuthorities()));
        // 更新登录信息
        redisTemplate.opsForValue().set(key, simpleUserDO, expirationTime, TimeUnit.HOURS);
        return token;
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
