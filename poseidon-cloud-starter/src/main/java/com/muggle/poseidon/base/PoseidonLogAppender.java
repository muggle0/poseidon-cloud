package com.muggle.poseidon.base;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.springframework.context.ApplicationContext;

/**
 * @program: poseidon-cloud-starter
 * @description:
 * @author: muggle
 * @create: 2020-03-10 11:46
 */
public class PoseidonLogAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    Layout<ILoggingEvent> layout;

    public PoseidonLogAppender() {

    }

    @Override
    protected void append(ILoggingEvent eventObject) {

    }
}
