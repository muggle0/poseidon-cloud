package com.muggle.poseidon.controller;


import com.muggle.poseidon.base.BaseController;
import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static com.muggle.poseidon.base.ResultBean.success;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muggle
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/document/test")
public class TestController extends BaseController {
    @Autowired
    TestService service;
    @GetMapping("/find")
    public ResultBean findAll(){
        service.set();
        return success();
    }

}
