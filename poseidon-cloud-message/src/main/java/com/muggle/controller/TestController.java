package com.muggle.controller;

import com.muggle.shchdule.TestMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/1$
 **/

@RestController
public class TestController {

    @PostMapping("/test")
    public String test( TestMessage testMessage){
//        String name = multipartFile.getName();
        MultipartFile file = testMessage.getFile();
        return "test";
    }

    @PostMapping("/")
    public String test2(@RequestParam("test") String test){
        System.out.println(test);
        return test;
    }
}
