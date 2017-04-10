package com.shanhh.demo.jedis;


import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * redis 静态调用类
 *
 * @author Dan
 * @since 2015-10-14 10:46
 */
@Component
public class JedisHelper implements JedisService {

    public <T> T doJedis(JedisCallback<T> jedisCallback, JedisPool jedisPool)
            throws CacheException {

        Jedis resource = checkRedisResource(jedisPool);
        T result = null;
        boolean resourceBroken = false;
        try {
            result = jedisCallback.doWithJedis(resource);
        } catch (JedisConnectionException e) {
            resourceBroken = true;
            returnBrokenResource(jedisPool, resource);
            throw new CacheException("Can not do jedis operation.", e);
        } catch (JedisDataException jde) {
            throw new RuntimeException(jde.getMessage(), jde);
        } finally {
            if (!resourceBroken) {
                releaseResource(jedisPool, resource);
            }
        }
        return result;
    }

    private void releaseResource(JedisPool jedisPool, Jedis resource) {
        if (resource != null) {
            jedisPool.returnResource(resource);
            resource = null;
        }
    }

    private Jedis checkRedisResource(JedisPool jedisPool) throws CacheException {
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            // 防止获取null资源
            if (resource == null) {
                CacheException exception = new CacheException("Jedis pool returned null resource");
                throw exception;
            }

            return resource;
        } catch (JedisConnectionException e) {
            String message = e.getMessage()
                    + ", get resource from Redis pool failed, please check the Redis server state.";
            CacheException exception = new CacheException(message, e);
            if (resource != null) {
                jedisPool.returnBrokenResource(resource);
            }
            throw exception;
        }
    }

    private void returnBrokenResource(JedisPool jedisPool, Jedis resource) {
        if (resource != null) {
            jedisPool.returnBrokenResource(resource);
            resource = null;
        }
    }
}
