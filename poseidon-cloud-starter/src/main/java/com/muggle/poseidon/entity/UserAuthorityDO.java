package com.muggle.poseidon.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * @program: poseidon-cloud-starter
 * @description:
 * @author: muggle
 * @create: 2020-03-10 10:58
 */
public class UserAuthorityDO implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String url;

    private String description;

    private Date gmtCreate;

    private boolean enable;

    private String requestType;

    private String application;

    private String className;

    private String methodName;

    private Long parentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getAuthority() {
        return requestType+":"+url;
    }
}
