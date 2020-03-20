package com.muggle.poseidon.tool;

import com.muggle.poseidon.util.IncrParmBean;
import com.muggle.poseidon.util.PoseidonIdGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: poseidon-cloud-user
 * @description: id生成器
 * @author: muggle
 * @create: 2019-12-06
 **/
@Service
public class SimpleIdGenerator extends PoseidonIdGenerator {
    RedisTemplate<String,Object> redisTemplate;
    Validator validator;
    public SimpleIdGenerator(){
        super(30, 13);
    }
    public SimpleIdGenerator(long datacenterId, long machineId, RedisTemplate redisTemplate) {
        super(datacenterId, machineId);
        this.redisTemplate=redisTemplate;
    }

    @Override
    public String getNextSerialNumber( IncrParmBean parmBean) {
       /* Set<ConstraintViolation<IncrParmBean>> validate = validator.validate(parmBean);
        // todo
        validate.forEach(err->{
            String message = err.getMessage();
        });
        Object o = redisTemplate.opsForValue().get(key);
        if (o==null){
            synchronized (this){
                Long increment = redisTemplate.opsForValue().increment(key, size);
                redisTemplate.expire(key,12, TimeUnit.HOURS);
                return this.getSerialString(perfix,key,increment);
            }
        }

        //加1
        Long number = redisTemplate.getConnectionFactory().getConnection().incr(
                redisTemplate.getKeySerializer().serialize(mark)
        );
        if (1==number){
            redisTemplate.expire(mark,properties.getIncrTime(),properties.getIncrUnit());
        }
        String lengthMatch=lengthMatch(number,lenght);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(properties.getGroup());
        stringBuilder.append(perfix).append(lengthMatch);
        return stringBuilder.toString();*/
        return null;
    }

    private String getSerialString(String perfix, String key, Long increment) {
        /*Double pow = Math.pow(10, lenght);
        long number=i%pow.intValue();
        String format = String.format("%0"+lenght+"d", number);*/
        return null;

    }

    @Override
    public List<String> getBatchSerialNumber(IncrParmBean parmBean) {
        return null;
    }


}
