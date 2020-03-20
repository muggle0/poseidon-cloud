package com.muggle.poseidon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-16 22:40
 */
@Data
@Document(collection = "app_log")
public class LogMessageDO implements Serializable {

    private static final long serialVersionUID = -6011491493114006533L;

    private String appName;

    private Date time;

    private String message;

    private String level;

    private String logger;
}
