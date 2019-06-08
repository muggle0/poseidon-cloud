package com.muggle.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_info")
public class TestController {


    @GetMapping("/test")
    public String test(){
        System.out.println(">>>>>>>>>>>>>>>");
        return ">>>>>";
    }
}
