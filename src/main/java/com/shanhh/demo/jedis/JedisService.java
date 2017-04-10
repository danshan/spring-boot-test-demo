package com.shanhh.demo.jedis;

import redis.clients.jedis.JedisPool;

/**
 * @author dan
 * @since 2017-04-10 15:29
 */
public interface JedisService {

    <T> T doJedis(JedisCallback<T> jedisCallback, JedisPool jedisPool);
}
