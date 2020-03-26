package com.muggle.poseidon.config;

import com.muggle.poseidon.base.exception.BasePoseidonCheckException;
import com.muggle.poseidon.base.exception.SimplePoseidonCheckException;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.store.SecurityStore;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;


/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/24$
 **/
public class CommonTokenStore implements SecurityStore {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${poseidon.credential}")
    private String credential;

    @Override
    public UserDetails getUserdetail(String token) throws BasePoseidonCheckException {
        String storeKey = JwtTokenUtils.getStoreKey(token, token);
        Object o = redisTemplate.opsForValue().get(storeKey);
        if (o==null){
            throw new SimplePoseidonCheckException("用户未登录，请先登录");
        }
        SimpleUserDO userDO = (SimpleUserDO) o;

        String random = JwtTokenUtils.getRandom(token, credential);
        boolean flag=false;
        for (String userDORandom : userDO.getRandoms()) {
            boolean equals = userDORandom.equals(random);
            if (equals) {
                flag=true;
            }
        }
        if (!flag){
            throw new SimplePoseidonCheckException("登录信息过期，请重新登陆");
        }
        UserInfo userInfo = new UserInfo();
        return userInfo;
    }

    @Override
    public String saveUser(UserDetails userDetails, long expirationTime, String key) {
        return null;
    }

    @Override
    public Boolean cleanToken(String token) {
        return null;
    }

    @Override
    public void setExpirationTime(long expirationTime, String token) {

    }

    @Override
    public UserDetails processToken(UserDetails userDetails, String token, Function<UserDetails, UserDetails> function) {
        return null;
    }
}
