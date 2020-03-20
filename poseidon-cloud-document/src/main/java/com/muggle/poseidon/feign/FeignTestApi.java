package com.muggle.poseidon.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-center")
@Service
public interface FeignTestApi {
    @RequestMapping("/test")
    String hello();
}
