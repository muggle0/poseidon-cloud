package com.muggle.poseidon.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String title;
    private String content;
}
