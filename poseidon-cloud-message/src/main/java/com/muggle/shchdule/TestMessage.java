package com.muggle.shchdule;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/1$
 **/
public class TestMessage {
    private String test;
    private MultipartFile file;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
