package com.muggle.poseidon.helper.impl;

import com.muggle.poseidon.helper.RequestLockHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2020-01-10
 **/

@Component
public class BlackListRequestLockHelper implements RequestLockHelper {
    @Override
    public boolean accsess(HttpServletRequest request) {
        return true;
    }
}
