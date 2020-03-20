package com.muggle.poseidon.controller;

import com.muggle.poseidon.base.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import static com.muggle.poseidon.base.ResultBean.successData;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-17 16:49
 */
@RestController
@RequestMapping("/doc/register")
public class RegisterController {
    @Autowired
    DiscoveryClient discoveryClient;
    @GetMapping("services")
    public ResultBean<List<String>> getServices(){
        List<String> services = discoveryClient.getServices();
        Iterator<String> iterator = services.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (!next.contains("center")){
                iterator.remove();
            }
        }
        return successData(services);
    }


}
