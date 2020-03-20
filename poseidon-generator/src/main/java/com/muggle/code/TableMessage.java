package com.muggle.code;

import java.util.List;
import java.util.Map;

/**
 * @program: poseidon-generator
 * @description: 表信息
 * @author: muggle
 * @create: 2019-12-05
 **/

public class TableMessage {

    /** 数据库密码*/
    private String password;

    /** 资料库用户名*/
    private String username;

    /** 数据库连接*/
    private String jdbcUrl;

    /** */
    private String module;

    private boolean swagger;

    private String author;

    private List<String> tableName;

    private String driver;

    private Map<String ,String> otherField;

    private String basePack;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isSwagger() {
        return swagger;
    }

    public void setSwagger(boolean swagger) {
        this.swagger = swagger;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Map<String, String> getOtherField() {
        return otherField;
    }

    public void setOtherField(Map<String, String> otherField) {
        this.otherField = otherField;
    }

    public String getBasePack() {
        return basePack;
    }

    public void setBasePack(String basePack) {
        this.basePack = basePack;
    }
}
