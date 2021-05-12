package com.alu.itoken.rabbitmq.service.hystrix;

import com.alu.itoken.rabbitmq.service.feign.RedisService;
import com.alu.itoken.rabbitmq.vo.KeyPrefix;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceHystrix implements RedisService {


    @Override
    public String put(String key, String value, long time) {
        return "request error";
    }

    @Override
    public Object get(String key) {
        return "request error";
    }

    @Override
    public String putB(String className, String key, String value) {
        return "request error";
    }

    @Override
    public Object getB(String className, String key) {
        return "request error";
    }

    @Override
    public Object get(KeyPrefix prefix, String key, Class clazz) {
        return "ohoh 服务异常";
    }

    @Override
    public boolean set(KeyPrefix prefix, String key, Object value) {
        return false;
    }

    @Override
    public boolean exists(KeyPrefix keyPrefix, String key) {
        return false;
    }

    @Override
    public long incr(KeyPrefix keyPrefix, String key) {
        return 0;
    }

    @Override
    public long decr(KeyPrefix keyPrefix, String key) {
        return 0;
    }

    @Override
    public boolean delete(KeyPrefix prefix, String key) {
        return false;
    }

}
