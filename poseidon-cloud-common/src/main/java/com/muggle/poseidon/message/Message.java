package com.muggle.poseidon.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {
    private String title;
    private T content;
    private String messageCode;
    private String messageId;
}
