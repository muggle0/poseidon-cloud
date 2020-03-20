package com.muggle.poseidon.controller;

import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muggle.poseidon.base.ResultBean.success;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2019-11-08
 **/

@RestController
@Api(value = "类注释")
@RequestMapping("/test")
public class TestController {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    IUserInfoService userInfoService;



    @GetMapping("/string")
    @ApiOperation(value = "方法注释")
    public ResultBean test(){
        return success();
    }

    @GetMapping("/insert")
    public ResultBean insert(){
        userInfoService.insert();

        return success();
    }



}
