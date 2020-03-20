package com.muggle.poseidon.config.security.config;

import com.muggle.poseidon.helper.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * @program: poseidon
 * @description: ProviderManager把工作委托给AuthenticationProvider集合。ProviderManager将所有AuthenticationProvider进行循环，直到运行返回一个完整的Authentication，不符合条件或者不能认证当前Authentication，返回AuthenticationException异常或者null。
 * @author: muggle
 * @create: 2018-09-18 19:27
 **/
@Slf4j
public class PoseidonAuthenticationProvider implements AuthenticationProvider {
    private Map<String, LoginHelper> loginHelperMap;

    public PoseidonAuthenticationProvider(Map<String, LoginHelper> loginHelperMap) {
        this.loginHelperMap = loginHelperMap;
    }


    /**
     * @Description: 验证器的验证方法
     * @Param: authentication 用户的登陆信息
     * @return:
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();
        String loginType = principal.get("loginType");
        LoginHelper loginHelper = loginHelperMap.get(loginType + "LoginHelper");
        log.debug("登陆方式错误：{}",loginType);
        if (loginHelper==null){
            throw new BadCredentialsException("登陆方式错误");
        }
        String credentials = (String) authentication.getCredentials();
        Authentication token = loginHelper.getAuth(principal.get("username"),credentials);
        return token;
    }

    /**
     * @Description: 判断验证器是否执行
     * @Param:
     * @return:
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(aClass));
    }

}
