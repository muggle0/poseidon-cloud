package com.muggle.poseidon.config.security.config;

import com.muggle.poseidon.auto.PoseidonSecurityProperties;
import com.muggle.poseidon.helper.LoginHelper;
import com.muggle.poseidon.config.security.filter.SecurityLoginFilter;
import com.muggle.poseidon.config.security.filter.SecurityTokenFilter;
import com.muggle.poseidon.config.security.handler.*;
import com.muggle.poseidon.config.security.properties.SecurityMessageProperties;
import com.muggle.poseidon.service.MessageService;
import com.muggle.poseidon.service.TokenService;
import com.muggle.poseidon.store.SecurityStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: poseidon-cloud-starter
 * @description: 权限校验配置类
 * @author: muggle
 * @create: 2019-11-04
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class PoseidonAuthConfigAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @SuppressWarnings("all")
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    SecurityStore securityStore;
    @Autowired
    PoseidonSecurityProperties properties;

    @Autowired
    TokenService tokenService;

    @Autowired
    MessageService messageService;
    @Autowired
    private Map<String, LoginHelper> loginHelperMap;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String [] paths={"/**/*.bmp", "/**/*.gif", "/**/*.png", "/**/*.jpg", "/**/*.ico","/**/*.html"};
        SecurityStore.ACCESS_PATHS.addAll(Arrays.asList(paths));
        web.ignoring().antMatchers(paths);
        log.debug("》》》》 初始化security 放行静态资源：{}" + "/**/*.bmp /**/*.png /**/*.gif /**/*.jpg /**/*.ico /**/*.js");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("》》》》 启动security配置");

        List<String> accessPaths = SecurityStore.ACCESS_PATHS;
        String [] paths=new String[accessPaths.size()];
        accessPaths.toArray(paths);

        http.authorizeRequests().antMatchers(paths).permitAll().antMatchers("/poseidon/**","/api/**").permitAll()
                .antMatchers("/admin/oauth/**").hasRole("admin")
                .anyRequest().authenticated().accessDecisionManager(accessDecisionManager())
                .and().csrf().disable();
        http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/public/login.html").loginProcessingUrl(SecurityMessageProperties.LOGIN_URL).permitAll();
        http.addFilterBefore(poseidonTokenFilter(), LogoutFilter.class);
        http.exceptionHandling().authenticationEntryPoint(loginUrlAuthenticationEntryPoint()).accessDeniedHandler(new PoseidonAccessDeniedHandler());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.addFilterAt(getLoginFilter(),UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutUrl(SecurityMessageProperties.LOGOUT).logoutSuccessHandler(getLogoutSuccessHandler()).permitAll();
    }


    /**
    * @author muggle
    * @Description: 设置投票器
    * @Param:
    * @return:
    * @date 2019/11/6 8:38
    */
    private AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new PoseidonWebExpressionVoter(tokenService,properties));
        return new UnanimousBased(decisionVoters);

    }

    protected AuthenticationManager getAuthenticationManager()  {
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(poseidonAuthenticationProvider()));
        return authenticationManager;
    }

    private Filter getLoginFilter() {

        SecurityLoginFilter poseidonTokenFilter = new SecurityLoginFilter(securityStore,properties,messageService);
        poseidonTokenFilter.setAuthenticationSuccessHandler(new PoseidonAuthenticationSuccessHandler());
        poseidonTokenFilter.setAuthenticationFailureHandler(new PoseidonAuthenticationFailureHandler());
        poseidonTokenFilter.setAuthenticationManager(getAuthenticationManager());
        poseidonTokenFilter.setFilterProcessesUrl(SecurityMessageProperties.LOGIN_URL);
        return poseidonTokenFilter;
    }
    PoseidonAuthenticationProvider poseidonAuthenticationProvider(){
        PoseidonAuthenticationProvider poseidonAuthenticationProvider=new PoseidonAuthenticationProvider(loginHelperMap);
        return poseidonAuthenticationProvider;
    }
    /**
    * @author muggle
    * @Description: token的过滤器
    * @Param:
    * @return:
    * @date 2019/11/6 8:38
    */
    private SecurityTokenFilter poseidonTokenFilter(){
        final SecurityTokenFilter poseidonTokenFilter = new SecurityTokenFilter(securityStore,properties);
        return poseidonTokenFilter;
    }

    /**
    * @author muggle
    * @Description: 未登陆处理器，当url为登陆权限时放行
    * @Param:
    * @return:
    * @date 2019/11/5 12:01
    */
    private AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new PoseidonLoginUrlAuthenticationEntryPoint(SecurityMessageProperties.LOGIN_URL);
    }



    /**
     * 登出成功处理器
     * @return
     */
    private LogoutSuccessHandler getLogoutSuccessHandler(){
        return new PoseidonLogoutSuccessHandler(this.securityStore);
    }
}
