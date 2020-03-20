package com.muggle.poseidon.tool;

import com.muggle.poseidon.base.exception.SimplePoseidonCheckException;
import com.muggle.poseidon.base.exception.SimplePoseidonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class RedisLockTool implements Lock {
    @Autowired
    StringRedisTemplate redisTemplate;
    private Long erperTime;
    private String key;
    private TimeUnit timeUnit;
    private Long timeout;


    public RedisLockTool(Long erperTime, String key, TimeUnit timeUnit,Long timeout) {
        this.erperTime = erperTime;
        this.key = key;
        this.timeUnit = timeUnit;
        this.timeout=timeout;
    }

    /**
     * redis 脚本
     * */
    private static final String unlockScript =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n"
                    + "then\n"
                    + "    return redis.call(\"del\",KEYS[1])\n"
                    + "else\n"
                    + "    return 0\n"
                    + "end";

    @Override
    public void lock() {
        try {
            String lock = this.lock(key, erperTime, timeout);
        } catch (SimplePoseidonCheckException e) {
            throw new SimplePoseidonException("上锁失败，未能获取锁");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //todo
    }

    @Override
    public boolean tryLock() {
        // todo
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // todo
        return false;
    }

    @Override
    public void unlock() {
        //todo
    }

    @Override
    public Condition newCondition() {
        return null;
    }



    /**
     * 加锁，有阻塞
     * @param name
     * @param expire
     * @param timeout
     * @return
     */
    public String lock(String name, long expire, long timeout) throws SimplePoseidonCheckException {
        // fixme
        long startTime = System.currentTimeMillis();
        String token;
        do{
            token = tryLock(name, expire);
            if(token == null) {
                long l = System.currentTimeMillis();
                if (l-startTime>timeout){
                    throw new SimplePoseidonCheckException("上锁失败,未能获取锁");
                }
                log.debug("》》》》》》 自旋中");
            }
        }while(token==null);

        return token;
    }

    /**
     * 加锁，无阻塞
     * @param name
     * @param expire
     * @return
     */
    public String tryLock(String name, long expire) {
        String token = UUID.randomUUID().toString();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection conn = factory.getConnection();
        try{
            Boolean result = conn.set(name.getBytes(Charset.forName("UTF-8")), token.getBytes(Charset.forName("UTF-8")),
                    Expiration.from(expire, this.timeUnit), RedisStringCommands.SetOption.SET_IF_ABSENT);
            if(result!=null && result)
                return token;
        }finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        return null;
    }

    /**
     * 解锁
     * @param name
     * @param token
     * @return
     */
    public boolean unlock(String name, String token) {
        byte[][] keysAndArgs = new byte[2][];
        keysAndArgs[0] = name.getBytes(Charset.forName("UTF-8"));
        keysAndArgs[1] = token.getBytes(Charset.forName("UTF-8"));
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection conn = factory.getConnection();
        try {
            Long result = (Long)conn.scriptingCommands().eval(unlockScript.getBytes(Charset.forName("UTF-8")), ReturnType.INTEGER, 1, keysAndArgs);
            if(result!=null && result>0)
                return true;
        }finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        return false;
    }
}
