package com.shanhh.demo.jedis;

import redis.clients.jedis.Jedis;

@FunctionalInterface
public interface JedisCallback<T> {
  T doWithJedis(Jedis jedis);
}