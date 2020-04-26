package com.muggle.shchdule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/31$
 **/
@Component
public class TestSchdule {
    private static final Logger log = LoggerFactory.getLogger(TestSchdule.class);
    @Autowired
    RestTemplate restTemplate;
    private static String url="https://oapi.dingtalk.com/robot/send?access_token=83da62f0215f9e847b34a3d69e228e4ce7d0633a410a348419a772dc879fca71&timestamp=%s&sign=%s";

//    @Scheduled(fixedRate = 50000)
    public void teestDing(){
    }

}
