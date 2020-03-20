package com.muggle.poseidon.controller;


import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.service.IUserInfoService;
import com.muggle.poseidon.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/user")
@Api(value = "用户增删改查",description = "详细描述")
public class UserController {

    private static String REGIST="regist:";

    @Autowired
    MessageService messageService;

    @Autowired
    IUserInfoService userInfoService;



    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户的唯一标识",required = true),
            @ApiImplicitParam(name = "password" ,value = "用户名" , required = false),
            @ApiImplicitParam(name = "gender" ,value = "性别" , required = false)
    })
    @PostMapping("/registration")
    public ResultBean registration(@RequestBody SimpleUserDO userDO, String verifType ){

//        if (verifCode==null){
//            throw new SimplePoseidonException("请输入验证码");
//        }
//        String verificat = messageService.getAndDeletVerificat(VerlifaTypeEnum.EMAIL.getType(), REGIST+userDO.getUsername());
//        if (!verifCode.equals(verificat)){
//            throw new SimplePoseidonException("验证码输入错误,请重新获取验证码");
//        }
//
//        userInfoService.registration(userDO);
        return ResultBean.success("注册成功");
    }

}
