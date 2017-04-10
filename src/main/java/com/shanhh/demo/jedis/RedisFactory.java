package com.shanhh.demo.jedis;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Dan
 * @since 2016-07-05 15:31
 */
@Data
public class RedisFactory {

  @NotNull
  private PoolCfg pool;
  @NotNull
  private ClientCfg client;

  @Data
  class PoolCfg {
    @Min(1)
    @Max(50)
    private int maxTotal;
    @Min(1)
    @Max(50)
    private int maxIdle;
    @Min(100)
    @Max(10000)
    private long maxWaitMillis;
    private boolean testOnBorrow;
  }

  @Data
  class ClientCfg {
    @NotEmpty
    private String server;
    @Min(1)
    @Max(65535)
    private int port;
    @Min(100)
    @Max(10000)
    private int timeout;
    @Min(0)
    @Max(15)
    private int db;
  }

  public JedisPool build() {
    JedisPoolConfig poolCfg = new JedisPoolConfig();
    poolCfg.setMaxTotal(pool.getMaxTotal());
    poolCfg.setMaxIdle(pool.getMaxIdle());
    poolCfg.setMaxWaitMillis(pool.getMaxWaitMillis());
    poolCfg.setTestOnBorrow(pool.isTestOnBorrow());

    return new JedisPool(
        poolCfg,
        client.getServer(),
        client.getPort(),
        client.getTimeout(),
        null,
        client.getDb());
  }

}
