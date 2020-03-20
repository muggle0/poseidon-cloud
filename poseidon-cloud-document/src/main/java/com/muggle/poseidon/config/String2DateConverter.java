package com.muggle.poseidon.config;

import com.muggle.poseidon.base.exception.SimplePoseidonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: poseidon-cloud-document
 * @description: MongoDB -> Java
 * @author: muggle
 * @create: 2020-03-17 14:12
 */
@ReadingConverter
public class String2DateConverter implements Converter<String, Date> {
    /** logger */
    private static final Logger log = LoggerFactory.getLogger(String2DateConverter.class);
    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(source);
            return parse;
        } catch (ParseException e) {
            return null;
        }
    }
}
