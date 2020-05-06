package com.muggle.servicce;

import com.muggle.poseidon.message.Message;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/5/6
 **/
public interface MailService {

    void sendSimpleMessage(Message content);
}
