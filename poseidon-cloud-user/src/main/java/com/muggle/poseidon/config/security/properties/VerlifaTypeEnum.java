package com.muggle.poseidon.config.security.properties;

public enum VerlifaTypeEnum {
    LOGIN("LOGIN:","登陆校验"),
    EMAIL("EMAIL:","采用email发送"),
    PHONE("PHONE:","采用手机号发送");
    private String type;
    private String name;

    VerlifaTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }


    public String getName() {
        return name;
    }

}
