package com.muggle.poseidon.controller;

import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.entity.LogMessageDO;
import com.muggle.poseidon.repository.AppLogMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.muggle.poseidon.base.ResultBean.success;

/**
 * @program: poseidon-cloud-document
 * @description: 日志相关的操作
 * @author: muggle
 * @create: 2020-03-14 21:09
 */


@RestController
@RequestMapping("/doc")
public class LogController {

    @Autowired
    AppLogMongoRepository repository;

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * https://www.cnblogs.com/longfurcat/p/10093281.html 操作api
     *
     * */
    @GetMapping("/log")
    public ResultBean<List<LogMessageDO>> getLog(LogMessageDO messageDO){
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("appName", ExampleMatcher.GenericPropertyMatchers.contains()).
                        withIgnoreCase(true).withIgnoreNullValues().withIgnorePaths("_class");

        Example<LogMessageDO> of = Example.of(messageDO,matcher);
        List<LogMessageDO> all= repository.findAll(of);
        return ResultBean.successData(all);
    }

    @GetMapping("/user_log")
    public  ResultBean<List<LogMessageDO>> getUserLog(){
        return success();
    }

}
