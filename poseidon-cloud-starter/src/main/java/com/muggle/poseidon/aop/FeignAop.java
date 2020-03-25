package com.muggle.poseidon.aop;

import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.base.exception.SimplePoseidonException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-21 22:37
 */
@Aspect
@SuppressWarnings("all")
public class FeignAop {

    @Pointcut("execution(* com.muggle.poseidon.feign..*.*(..))")
    public void feign() {
    }

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(FeignAop.class);
    /*@Before("feign()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

    }*/
// @Before,@Around,@After,@AfterReturn,@AfterThrowing
    /*@After("feign()")
    public void test(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
    }*/
    @AfterReturning(pointcut = "feign()",returning = "result")
    public void message(JoinPoint joinPoint, Object result){
        if (result instanceof ResultBean){
            ResultBean resultBean = (ResultBean) result;
            Integer code = resultBean.getCode();
            if (code!=200){
                throw new SimplePoseidonException(resultBean.getMessage());
            }
        }
    }

//    value = "execution(* com.spring.aop.impl.ArithmeticCalculatorImpl.*(int,int))  && args(i,j)", argNames = "point, i,j"
    @AfterThrowing(pointcut = "execution(* com.muggle.poseidon.feign..*.*(..))",throwing = "ex")
    public void th(JoinPoint joinPoint,Throwable ex){
//      log.error("feign 调用异常：",ex);
        throw new SimplePoseidonException("未响应，请稍后再试");
    }

}
