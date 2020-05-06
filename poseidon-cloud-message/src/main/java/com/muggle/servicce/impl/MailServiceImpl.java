package com.muggle.servicce.impl;

import com.muggle.poseidon.message.Message;
import com.muggle.servicce.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/5/6
 **/
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(Message message){
        SimpleMailMessage target = new SimpleMailMessage();
        target.setTo(message.getMessageCode());//收信人
        target.setSubject(message.getTitle());//主题
        target.setText();//内容
        target.setFrom(from);//发信人

        mailSender.
    }
}
