package com.shrimp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.function.*;

/**
 * 功能说明: 封装redis<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-13<br>
 */
@Service
public class RedisService {

    @Autowired
    private ShardedJedisPool shardedJedisPool;


    private <T> T execute(Function<ShardedJedis, T> fun) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return fun.apply(shardedJedis);
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
    }

    /**
     * 执行set操作
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.execute((e) -> {
            return e.set(key, value);
        });
    }

    /**
     * 执行get操作
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return execute((e) -> {
            return e.get(key);
        });
    }


    /**
     * 执行删除操作
     *
     * @param key
     * @return
     */
    public Long del(final String key) {
        return this.execute((e) -> {
            return e.del(key);
        });
    }


    /**
     * 设置生存时间，单位为：秒
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return this.execute((e) -> {
            return e.expire(key, seconds);
        });
    }


    /**
     * 执行set操作并且设置生存时间，单位为：秒
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute((e) -> {
            String str = e.set(key, value);
            e.expire(key, seconds);
            return str;
        });
    }

}

