package com.muggle.poseidon.config.security.store.impl;

import com.muggle.poseidon.base.exception.BasePoseidonCheckException;
import com.muggle.poseidon.store.SecurityStore;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2019-12-02
 **/


//@Service
public class TestSecurityStore implements SecurityStore {

    @Override
    public UserDetails getUserdetail(String token) throws BasePoseidonCheckException {
        return null;
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
