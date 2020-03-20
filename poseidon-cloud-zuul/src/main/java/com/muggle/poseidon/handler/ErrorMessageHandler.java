package com.muggle.poseidon.handler;

import com.muggle.poseidon.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: poseidon-cloud-zuul
 * @description:
 * @author: muggle
 * @create: 2020-03-11 18:00
 */

@RestController
public class ErrorMessageHandler implements ErrorController {

    @Value("${spring.application.name}")
    private String appName;
    /** logger */
    private static final Logger log = LoggerFactory.getLogger(ErrorMessageHandler.class);
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping(value = "/error",produces = "application/json;charset=UTF-8")
    public ResultBean error(HttpServletRequest request){
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        log.error("请求异常 错误码：{}",statusCode);
        if (statusCode==500){
            return ResultBean.error("请求未响应，请稍后再试");
        }else {
            return ResultBean.error("请求未响应，请稍后再试");
        }
    }

    @RequestMapping("/")
    public ResultBean getMessage() {
        return ResultBean.successData(appName);
    }
}
