package com.muggle.poseidon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/4/2$
 **/


@Component
public class ExceptionListener implements ApplicationListener<ExceptionEvent> {


    @Override
    public void onApplicationEvent(ExceptionEvent event) {
        // todo
        int i=0;
        while (i<3)
        try {
            String message = event.getMessage();
//            DingUtil.sendMessage(DingParamProperties.DING_URL, DingSendEnum.TEXT,DingParamProperties.SECRET,message);

            break;
        }catch (Exception e){
            i++;
        }
    }
}
