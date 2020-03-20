package com.muggle.poseidon.config;

import com.muggle.poseidon.base.exception.BasePoseidonCheckException;
import com.muggle.poseidon.store.SecurityStore;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-17 12:53
 */
@Service
public class TokenStoreImpl implements SecurityStore {
    @Override
    public UserDetails getUserdetail(String s) throws BasePoseidonCheckException {
        return null;
    }

    @Override
    public String saveUser(UserDetails userDetails, long l, String s) {
        return null;
    }

    @Override
    public Boolean cleanToken(String s) {
        return null;
    }

    @Override
    public void setExpirationTime(long l, String s) {

    }

    @Override
    public UserDetails processToken(UserDetails userDetails, String s, Function<UserDetails, UserDetails> function) {
        return null;
    }
}
