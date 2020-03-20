package com.muggle.poseidon.controller;


import com.muggle.poseidon.base.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muggle.poseidon.base.ResultBean.success;


/**
 * 数据字典查询与设置
 */
@RestController
@RequestMapping("/doc/data")
public class DataDictionaryController {

    @GetMapping("/find")
    public ResultBean findAll(){
        return success();
    }
}
