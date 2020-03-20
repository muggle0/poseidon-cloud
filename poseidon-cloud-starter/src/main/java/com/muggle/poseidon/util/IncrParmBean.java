package com.muggle.poseidon.util;



import java.util.concurrent.TimeUnit;

/**
 * @program: poseidon-cloud-core
 * @description:
 * @author: muggle
 * @create: 2020-03-15 18:45
 */
public class IncrParmBean {
    /* 自增的个数*/
    private Long size;
    private Long expire;
    /*过期的时间*/
    private TimeUnit timeUnit;
    private String key;
    /*前缀*/
    private String perfix;
    /*数字长度*/
    private Long length;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPerfix() {
        return perfix;
    }

    public void setPerfix(String perfix) {
        this.perfix = perfix;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
