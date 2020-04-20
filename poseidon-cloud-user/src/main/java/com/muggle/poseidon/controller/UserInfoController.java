package com.muggle.poseidon.controller;


import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.config.security.properties.SecurityMessageProperties;
import com.muggle.poseidon.config.security.properties.VerlifaTypeEnum;
import com.muggle.poseidon.service.IUserInfoService;
import com.muggle.poseidon.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.muggle.poseidon.base.BaseController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/poseidon/user-info")
public class UserInfoController extends BaseController {

    @Autowired
    MessageService messageService;

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping("/public/verification_code")
    public ResultBean<String> getCode(String username){
        String result = messageService.setAndGetVerificat(VerlifaTypeEnum.LOGIN.name(), SecurityMessageProperties.VERIFICATION + username);
        return ResultBean.successData(result);
    }


    @GetMapping("/test")
    public ResultBean<String> test(){
        userInfoService.test0();
        return ResultBean.successData("测试");
    }
}
