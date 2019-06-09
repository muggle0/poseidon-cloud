package com.muggle.temp;

import com.muggle.base.EmailBean;

public interface EmailTemplate {

    void  sendSimpleMail(EmailBean mailBean);

    void sendMailForHtml(EmailBean mailBean);

    void sendCode(String recipient,String context);
}
