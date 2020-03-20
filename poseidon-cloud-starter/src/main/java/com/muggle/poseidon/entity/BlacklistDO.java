package com.muggle.poseidon.entity;

import java.util.Date;

/**
 * @description: 用于保存所有黑名单用户
 * @author: mozishu
 * @create: 2020-01-02
 */
public class BlacklistDO {
    private Long id;
    private String ip;
    private Date ipTime;

    public BlacklistDO() {
    }

    public BlacklistDO(Long id, String ip, Date ipTime) {
        this.id = id;
        this.ip = ip;
        this.ipTime = ipTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getIpTime() {
        return ipTime;
    }

    public void setIpTime(Date ipTime) {
        this.ipTime = ipTime;
    }
}
