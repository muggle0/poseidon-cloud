package com.muggle.poseidon.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.service.IUserInfoService;
import com.muggle.poseidon.user.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.muggle.poseidon.base.ResultBean.success;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2019-11-08
 **/

@RestController
@Api(value = "类注释")
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/string")
    @ApiOperation(value = "方法注释")
    public ResultBean test() {
        throw new RuntimeException("我被猪@了");
//        return success();
    }

    @PostMapping("/insert")
    public ResultBean insert(@RequestBody UserInfo userInfo) {

        List<String> services = discoveryClient.getServices();
//        List<ServiceInstance> instances = discoveryClient.getInstances(services);
//        instances.iterator().next().
       /* try {
            NamingService naming = NamingFactory.createNamingService(System.getProperty("serveAddr"));
//            naming.deregisterInstance("document-center", InetAddress.getLocalHost().getHostAddress(),8210);
        } catch (NacosException e) {
            e.printStackTrace();
        }*/

        userInfoService.insert();

        return success();
    }

}
