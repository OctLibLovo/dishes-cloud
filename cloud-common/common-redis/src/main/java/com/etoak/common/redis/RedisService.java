package com.etoak.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * string 类型setex
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public void setex(String key, String value, long expire, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * hash hset
     */
    public void hset(String key, String fields, String value) {
        stringRedisTemplate.opsForHash().put(key, fields, value);
    }

    /**
     * hash hgetall
     */
    public Map<String, String> hgetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key).entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey().toString(), entry -> entry.getValue().toString()
        ));
    }

    /**
     * hash hdel
     */
    public void hdel(String key, String ... fields){
        stringRedisTemplate.opsForHash().delete(key, fields);
    }
}
