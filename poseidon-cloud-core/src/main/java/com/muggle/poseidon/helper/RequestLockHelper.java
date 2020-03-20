package com.muggle.poseidon.helper;

import javax.servlet.http.HttpServletRequest;

public interface RequestLockHelper {

    boolean accsess(HttpServletRequest request);
}
