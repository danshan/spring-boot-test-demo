package com.shanhh.demo.jedis;

/**
 * @author Dan
 * @since 2015-10-14 10:50
 */
public class CacheException extends RuntimeException {
  public CacheException(String message, Throwable cause) {
    super(message, cause);
  }

  public CacheException(String message) {
    super(message);
  }
}
