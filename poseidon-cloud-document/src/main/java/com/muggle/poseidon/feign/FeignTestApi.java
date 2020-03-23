package com.muggle.poseidon.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "document-center")
public interface FeignTestApi {
    @RequestMapping("/xxxxxxxxx/test")
    String hello();
}
