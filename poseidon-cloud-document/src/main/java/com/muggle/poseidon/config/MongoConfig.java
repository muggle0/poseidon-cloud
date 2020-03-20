package com.muggle.poseidon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-17 14:21
 */
@Configuration
public class MongoConfig {

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        converterList.add(new String2DateConverter());
        return new CustomConversions(converterList);
    }
}
