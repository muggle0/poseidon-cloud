package com.muggle.poseidon.feign;

import com.muggle.poseidon.base.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-18 15:40
 */
@FeignClient("document-center")
public interface DocumentSeataFeign {

    @GetMapping("/document/test/find")
    ResultBean findAll();
}
